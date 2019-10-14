package com.cs336.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.PasswordAuthentication;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;


import com.cs336.util.SendMail;



/**
 * host:smtp.gmail.com
 * port:587
 * 
 */

/**
 * Servlet implementation class ForgotPasswordController
 */
@WebServlet("/ForgotPasswordController")
public class ForgotPasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		   response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    
		    String username=request.getParameter("uname");
		    String email=request.getParameter("email");
		    
		    if(checkAccount(username,email)==true) {
		    	 String buymepass = getPassword(username,email);
		    	 buymepass= buymepass.concat(" is your BuyMe password. Have a great day!");
		    	 String to = email;
		         String subject = "Your password request from BuyMe";
		         String user = "sbudhathoki789@gmail.com";
		         String password="BuyMeProject2019";
		       SendMail.send(to,subject,buymepass,user,password);  
		       out.println("<script type=\"text/javascript\">");
    	       out.println("alert('Your password haas been emailed to you..');");
    	       out.println("</script>");
    	       RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
	            rs.include(request, response);
		         
		    }else {
		    	 out.println("<script type=\"text/javascript\">");
	    	       out.println("alert('Userid or email incorrect..try again');");
	    	       out.println("</script>");
	            RequestDispatcher rs = request.getRequestDispatcher("forgotpass.jsp");
	            rs.include(request, response);
		    }
		
	}

	private String getPassword(String username, String email) {
		
		String password=new String();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
	try {
			
			//establish connection
			myConn= dataSource.getConnection();
			myStmt =myConn.prepareStatement
                    		("select * from user where user_name=? and user_email=?");
			myStmt.setString(1, username);
			myStmt.setString(2,email);
			
			myRs= myStmt.executeQuery();
			
			if(myRs.next()) {
				 password= myRs.getString(2);
					
			}
	
			 ServletContext context = getServletContext( );
			 context.log(password);
			 
				}
		
	catch(Exception exc) {
		exc.printStackTrace();
	}
	finally {
		close(myConn,myStmt,myRs);
	}
		
		return password;
	}


	private boolean checkAccount(String username, String email) {

		boolean check=false;
		
		// initialize connection variable
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
	
		try {
			
			//establish connection
			myConn= dataSource.getConnection();
			myStmt =myConn.prepareStatement
                    		("select * from user where user_name=? and user_email=?");
			myStmt.setString(1, username);
			myStmt.setString(2,email);
			
			myRs= myStmt.executeQuery();
			
			check=myRs.next();
			
				}
		
	catch(Exception exc) {
		exc.printStackTrace();
	}
	finally {
		close(myConn,myStmt,myRs);
	}
		return check;
		
	}
	
	//close connection

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
