package com.cs336.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.cs336.javafiles.Item;
import com.cs336.util.SendMail;
import com.cs336.util.auctionDbUtil;

/**
 * Servlet implementation class MyAccountController
 */
@WebServlet("/MyAccountController")
public class MyAccountController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private auctionDbUtil new_auctionDbUtil ;
	
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
	
	 public void init() throws ServletException {
	
			try {
				new_auctionDbUtil = new auctionDbUtil(dataSource);
				
			}catch (Exception exc){
				throw new ServletException(exc);
			}
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	

		try {
		String theCommand= request.getParameter("command");
		
		if(theCommand==null) {
			theCommand="showLiveAuctions";
		}
		
		switch(theCommand) {
		
		case "showLiveAuctions":
			showLiveAuctions(request,response);
			break;
		
		case "deleteaccount":
			deleteaccount(request,response);
			break;
		
		case "changepassword":
			changepassword(request,response);
			break;
		
		}
		}catch(Exception e) {
			e.printStackTrace();
		
		}
}

	private void showLiveAuctions(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<Item> auction_list = new_auctionDbUtil.getAuctionDetails();
		
		 request.setAttribute("AUCTIONLIST", auction_list);
		 RequestDispatcher dispatcher3= request.getRequestDispatcher("Homepage.jsp");
			dispatcher3.forward(request, response);
	}

	private void changepassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		  response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    
		String email= request.getParameter("email");
		String cpass=request.getParameter("cpass");
		String npass=request.getParameter("npass");
		
		//if user input exists in database
		if(checkPassword(email,cpass)==true) {
		
			updatePassword(email,npass);
			
			 npass= npass.concat(" is your new BuyMe password. Have a great day!");
	    	 String to = email;
	         String subject = "Your BuyMe password has been changed";
	         String user = "sbudhathoki789@gmail.com";
	         String password="_ZtzyuW917";
	       SendMail.send(to,subject,npass,user,password); 
			 out.println("<script type=\"text/javascript\">");
  	       out.println("alert('Your password has been changed..please log back in');");
  	       out.println("</script>");
  	       RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
	            rs.include(request, response);
			
		}else {
			   
			   out.println("<script type=\"text/javascript\">");
	  	       out.println("alert('Email or cpassword doesnot match our records..try again');");
	  	       out.println("</script>");
	  	       RequestDispatcher rs = request.getRequestDispatcher("changepassword.jsp");
		            rs.include(request, response);
				
		}
	}

	private boolean checkPassword(String email, String cpass) {
			boolean checker=false;
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn=dataSource.getConnection();
			
			//establish connection
			myConn= dataSource.getConnection();
			myStmt =myConn.prepareStatement
                    		("select * from user where user_password=? and user_email=?");
			myStmt.setString(1, cpass);
			myStmt.setString(2, email);
			
			myRs= myStmt.executeQuery();
			
			if(myRs.next()) {
				checker=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);
	
		}
		
		return checker;
	}
	
private void updatePassword(String email, String npass) {
	Connection myConn=null;
	PreparedStatement myStmt=null;
	ResultSet myRs =null;
	
	try {
		
		myConn=dataSource.getConnection();
		
		//establish connection
		myConn= dataSource.getConnection();
		myStmt =myConn.prepareStatement
                		("update user set user_password= ? where user_email=?");
		myStmt.setString(1, npass);
		myStmt.setString(2, email);
		 myStmt.executeUpdate();
		
	}catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		close(myConn,myStmt,null);

	}
		
	}


	private void deleteaccount(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

		  response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    
		    String email= request.getParameter("email");
		    String username = request.getParameter("uname");
		    String password = request.getParameter("pass");
		    
		    if(checkAccount(email,username,password)==true) {
		    	deleteNow(username,password);
				   out.println("<script type=\"text/javascript\">");
		  	       out.println("alert('Account has been deleted');");
		  	       out.println("</script>");
		  	       RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
			            rs.include(request, response);
		    } else {
		    	 out.println("<script type=\"text/javascript\">");
		  	       out.println("alert('Invalid username or password or email..please try again');");
		  	       out.println("</script>");
		  	       RequestDispatcher rs = request.getRequestDispatcher("deleteaccount.jsp");
			            rs.include(request, response);
		    }
	}
	

	private boolean checkAccount(String email, String username, String password) {
		
		boolean checker=false;
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn=dataSource.getConnection();
			
			//establish connection
			myConn= dataSource.getConnection();
			myStmt = myConn.prepareStatement
                   ("select * from user where user_email=? and user_name=? and user_password=?");
			myStmt.setString(1, email);
			myStmt.setString(2, username);
			myStmt.setString(3, password);
			
			myRs= myStmt.executeQuery();
			
			if(myRs.next()) {
				checker=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);
	
		}
		
		return checker;
	}

	private void deleteNow(String username, String password) {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn=dataSource.getConnection();
			
			myConn= dataSource.getConnection();
			myStmt =myConn.prepareStatement
	                ("delete from user where user_name=? and user_password=?");
			myStmt.setString(1, username);
			myStmt.setString(2, password);
			 myStmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);

		}
		
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
