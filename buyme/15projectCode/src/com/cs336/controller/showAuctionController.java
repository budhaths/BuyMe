package com.cs336.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.cs336.javafiles.BidHistory;
import com.cs336.javafiles.Item;
import com.cs336.util.auctionDbUtil;
import com.cs336.util.autoBidUtil;

/**
 * Servlet implementation class showAuctionController
 */
@WebServlet("/showAuctionController")
public class showAuctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
		private auctionDbUtil new_auctionDbUtil ;
		private autoBidUtil new_autoBidUtil;
		
		@Resource(name="jdbc/cs336db")
		private DataSource dataSource;
		
		 public void init() throws ServletException {
				try {
					new_auctionDbUtil = new auctionDbUtil(dataSource);
					new_autoBidUtil = new autoBidUtil(dataSource);
					
				}catch (Exception exc){
					throw new ServletException(exc);
				}
			}
		 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request,response);
		}
		 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//need to check if owner is placing bid on his own item
		try {
		String theCommand= request.getParameter("command");
		
		if(theCommand==null) {
			theCommand="show_particular_auction";
		}
		
		switch(theCommand) {

		case "show_particular_auction":
			show_particular_auction(request,response);
			break;
		
		case "new_bid_submit":
			new_bid_submit(request,response);
			break;
			
		case "auto_bidding_submit":
			auto_bidding_submit(request,response);
			break;
			
		case "deleteautoBid":
			deleteautoBid(request,response);
		}
		
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private void deleteautoBid(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
		  Connection myConn=null;
		 	PreparedStatement myStmt=null;
			ResultSet myRs =null;
			String user_name= request.getParameter("user_name");
			int item_id= Integer.parseInt(request.getParameter("autobid_number"));
			try {
		
					myConn=dataSource.getConnection();
					String sql="delete from AutoBidding where user_name=? and item_id=?";
					myStmt=myConn.prepareStatement(sql);
					myStmt.setString(1, user_name);
					myStmt.setInt(2, item_id);
					myStmt.executeUpdate();
					
			} catch(Exception e) {
					e.printStackTrace();
			}
				finally {
						close(myConn, myStmt, myRs);
				}	
			

	 		 out.println("<script type=\"text/javascript\">");
	 	       out.println("alert('Auto bid has been deleted');");
	 	       out.println("</script>");
	 	       

	 			 RequestDispatcher dispatcher4= request.getRequestDispatcher("customer_rep_portal.jsp");
	 				dispatcher4.forward(request, response);
			
			
	}

	private void auto_bidding_submit(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    float auto_bid = Float.parseFloat(request.getParameter("new_auto_bid"));
	    int item_id = Integer.parseInt(request.getParameter("item_id"));
	    HttpSession session=request.getSession(false);
		 String user_name = (session.getAttribute("user")).toString();
	    new_autoBidUtil.setNewAutoBid(user_name,item_id,auto_bid);
	    show_particular_auction(request,response); 
		
	}

	private void new_bid_submit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	   // ServletContext context = getServletContext( );
	 
		float new_bid =Float.parseFloat(request.getParameter("new_bid"));
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		  HttpSession session=request.getSession(false);
		  String buyer = (session.getAttribute("user")).toString();
		  //context.log(buyer);

 		 out.println("<script type=\"text/javascript\">");
 	       out.println("alert('Bid has been placed');");
 	       out.println("</script>");
 	       
		  new_auctionDbUtil.updateAfterNewBid(new_bid,item_id,buyer);
		  
		  //now check if autobid exists autobid
		   Connection myConn=null;
		 	PreparedStatement myStmt=null;
			ResultSet myRs =null;
		
			float max_price = 0;
			String auto_bid_buyer_name=null;
			
			try {
			
				myConn =dataSource.getConnection();
				String sql= "select * from AutoBidding where item_id=?";
				myStmt=myConn.prepareStatement(sql);
				myStmt.setInt(1, item_id);
				myRs=myStmt.executeQuery();
				
				while(myRs.next()) {
					
					auto_bid_buyer_name=myRs.getString("user_name");
					max_price=myRs.getFloat("max_price");
				}
				if(max_price==0 && auto_bid_buyer_name==null) {
					 show_particular_auction(request,response); 
				}
				
				if(new_bid<max_price) {
					new_bid++;
					//if the user placed bid is less then auto bid
					new_auctionDbUtil.updateAfterNewBid(new_bid, item_id, auto_bid_buyer_name);
				}else {
					new_autoBidUtil.deleteAutoBid(item_id,auto_bid_buyer_name);
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
			finally {
				close(myConn, myStmt, myRs);
			}	
		  
		 show_particular_auction(request,response); 
	
	}

	private void show_particular_auction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int item_id = Integer.parseInt(request.getParameter("item_id"));
		Item item_list_5 = new_auctionDbUtil.getItemDetails(item_id);
		List<BidHistory> bid_history = new_autoBidUtil.getBidHistory(item_id);
		request.setAttribute("SHOWITEM", item_list_5);
		request.setAttribute("BHL", bid_history);
		request.setAttribute("itemid", item_id);
		
		 RequestDispatcher dispatcher4= request.getRequestDispatcher("showAuction.jsp");
			dispatcher4.forward(request, response);
		
	}

	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		// TODO Auto-generated method stub
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close(); 
			}
			if(myConn!=null) {
				myConn.close();
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}
	
	

}
