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
 * Servlet implementation class setAlertController
 */
@WebServlet("/setAlertController")
public class setAlertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html;charset=UTF-8");
		    PrintWriter out = response.getWriter();
		    
		HttpSession session=request.getSession(false);
	    String user_name = (session.getAttribute("user")).toString();
	    String category=request.getParameter("category");
	    float size=Float.parseFloat(request.getParameter("size"));
	    String color= request.getParameter("color");
	    
	    Connection myConn=null;
		PreparedStatement myStmt=null;
		
	    try {
	    	
	    		myConn=dataSource.getConnection();
			
			String sql="insert into ItemAlertsList"
				+"(user_name,category,size,color)"
				+"values(?,?,?,?)";
			
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, user_name);
			myStmt.setString(2, category);
			myStmt.setFloat(3, size);
			myStmt.setString(4, color);
			myStmt.execute();
			
	    }catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			close(myConn,myStmt,null);
	
		}
	    
	    out.println("<script type=\"text/javascript\">");
	       out.println("alert('You will be alerted when item is up or action');");
	       out.println("</script>");
	       
	       RequestDispatcher rs = request.getRequestDispatcher("setAlerts.jsp");
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
