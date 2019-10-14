package com.cs336.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    String username= request.getParameter("username");
	    String password= request.getParameter("password");
	    
	    if(validate(username,password)==true) {
			
			HttpSession session = request.getSession();
			session.setAttribute("user", username);
			//setting session to expiry in 30 mins
			session.setMaxInactiveInterval(30*60);
			
			Cookie user_name = new Cookie("user", username);
			response.addCookie(user_name);
			
	    	RequestDispatcher rs = request.getRequestDispatcher("MyAccountController");
            rs.forward(request, response);
            
            
	    }else {
	    	
	    		 out.println("<script type=\"text/javascript\">");
	    	       out.println("alert('User or password incorrect');");
	    	       out.println("</script>");
	            RequestDispatcher rs = request.getRequestDispatcher("login.jsp");
	            rs.include(request, response);
	         
	    }
	    		
	}


private boolean validate(String username, String password) {
		
		boolean check=false;
		
		// initialize connection variable
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
	
		try {
			
			//establish connection
			myConn= dataSource.getConnection();
			myStmt =myConn.prepareStatement
                    		("select * from user where user_name=? and user_password=?");
			myStmt.setString(1, username);
			myStmt.setString(2,password);
			
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
