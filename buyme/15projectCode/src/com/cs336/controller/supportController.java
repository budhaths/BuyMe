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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

/**
 * Servlet implementation class supportController
 */
@WebServlet("/supportController")
public class supportController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
				try {
				String theCommand= request.getParameter("command");
				
				
				switch(theCommand) {

				case "supportmessage":
					supportMessage(request,response);
					break;
					
				case "reply":
					replyMessage(request,response);
					break;
			    
				}
				
				} catch(Exception e) {
					e.printStackTrace();
				}
		
	}

	private void replyMessage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		 response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		String mail_from="BuyMe Support";
		String mail_to =request.getParameter("username"); 
		String message=request.getParameter("subject");
		try {
			 myConn=dataSource.getConnection();
			 String sql="insert into email (mail_to,mail_from,message) values (?,?,?)";
			 myStmt=myConn.prepareStatement(sql);
			 myStmt.setString(1, mail_to);
			 myStmt.setString(2, mail_from);
			 myStmt.setString(3, message);
			 myStmt.executeUpdate();
				
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
		
		out.println("<script type=\"text/javascript\">");
	       out.println("alert('Message replied');");
	       out.println("</script>");
	       RequestDispatcher rs = request.getRequestDispatcher("customerRepHandler");
	       rs.include(request, response);
	}

	private void supportMessage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		 response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		String mail_to="support";
		String mail_from =request.getParameter("username"); 
		String message=request.getParameter("subject");
		try {
			 myConn=dataSource.getConnection();
			 String sql="insert into email (mail_to,mail_from,message) values (?,?,?)";
			 myStmt=myConn.prepareStatement(sql);
			 myStmt.setString(1, mail_to);
			 myStmt.setString(2, mail_from);
			 myStmt.setString(3, message);
			 myStmt.executeUpdate();
				
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
		
		out.println("<script type=\"text/javascript\">");
	       out.println("alert('Message send.. customer rep will get back to you shortly');");
	       out.println("</script>");
	       RequestDispatcher rs = request.getRequestDispatcher("support.jsp");
	       rs.include(request, response);
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
