package com.cs336.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.cs336.javafiles.Item;
import com.cs336.util.auctionDbUtil;
import com.cs336.util.my_auctionHandler;

/**
 * Servlet implementation class my_auctionController
 */
@WebServlet("/my_auctionController")
public class my_auctionController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private my_auctionHandler  new_my_auctionHandler ;
	
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
	
	 public void init() throws ServletException {
	
			try {
				new_my_auctionHandler= new my_auctionHandler(dataSource);
				
			}catch (Exception exc){
				throw new ServletException(exc);
			}
		}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
		String theCommand= request.getParameter("command");
		
		if(theCommand==null) {
			theCommand="my_auctions";
		}
		
		switch(theCommand) {
		
		case "my_auctions":
			show_my_auction(request,response);
			break;	
			
		case "delete":
			deleteAuction(request,response);
			break;	
		}
			
		}catch(Exception e) {
			e.printStackTrace();
		
		}
	
	}

	private void deleteAuction(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
		int item_id= Integer.parseInt(request.getParameter("item_id_1"));
		new_my_auctionHandler.deleteItem(item_id);
		out.println("<script type=\"text/javascript\">");
		out.println("alert('Auction has been deleted..Redirecting to HomePage');");
	       	 out.println("</script>");
	      show_my_auction(request,response); 	 	
	}

	private void show_my_auction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		 String user_name = (session.getAttribute("user")).toString();	 
		List<Item> myauction_list = new_my_auctionHandler.showMyAuctions(user_name);
		request.setAttribute("myauction_list", myauction_list);
		
		 RequestDispatcher dispatcher5= request.getRequestDispatcher("my_auction_items.jsp");
			dispatcher5.forward(request, response);
	}
	
	

}
