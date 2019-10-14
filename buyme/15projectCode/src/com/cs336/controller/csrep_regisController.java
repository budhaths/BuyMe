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
import javax.sql.DataSource;

import com.cs336.javafiles.User;

@WebServlet("/csrep_regisController")
public class csrep_regisController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
	    String firstname = request.getParameter("fname");
	    String lastname = request.getParameter("lname");
	    String username= request.getParameter("username");
	    String password= request.getParameter("password");
	    String email= request.getParameter("email");
	    
	   
	    User newuser= new User(firstname,lastname,email,username,password);
	    
	    if(checkDublicate(username,email)==true) {
	    	out.println("<script type=\"text/javascript\">");
	    	 out.println("alert('User name or email already exists..please try again');");
 	       	 out.println("</script>");
 	       	 RequestDispatcher rs = request.getRequestDispatcher("admin_portal.jsp");
 	       	 rs.include(request, response);
	    }
	    
	    if(insertNewUser(newuser)==true ) {
	    	insertRep(username);
	    	 out.println("<script type=\"text/javascript\">");
	    	 out.println("alert('New Customer Representative created');");
  	       	 out.println("</script>");
          RequestDispatcher rs = request.getRequestDispatcher("admin_portal.jsp");
          rs.include(request, response);
	    }
	  
	}


	private void insertRep(String username) {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try {
			
			myConn=dataSource.getConnection();
			
			String sql="insert into customer_rep"
				+"(user_name)"
				+"values(?)";
			
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, username);
			myStmt.execute();

		}catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			close(myConn,myStmt,null);
	
		}
		
	}

	private boolean checkDublicate(String username, String email) {
		boolean checker=false;
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
	try {
			
			//establish connection
			myConn= dataSource.getConnection();
			myStmt =myConn.prepareStatement
                    		("select * from user where user_name=? or user_email=?");
			myStmt.setString(1, username);
			myStmt.setString(2,email);
			
			myRs= myStmt.executeQuery();
			
			if(myRs.next()) {
				checker=true;
					
			}
		}
		
	catch(Exception exc) {
		exc.printStackTrace();
	}
	finally {
		close(myConn,myStmt,myRs);
	}
		return checker;
	}

	private boolean insertNewUser(User newuser) {
		boolean checker=false;
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		
		try {
			
			myConn=dataSource.getConnection();
			
			String sql="insert into user"
				+"(user_name,user_password,first_name,last_name,user_email)"
				+"values(?,?,?,?,?)";
			
			myStmt=myConn.prepareStatement(sql);
			
			myStmt.setString(1, newuser.getUsername());
			myStmt.setString(2, newuser.getPassword());
			myStmt.setString(3, newuser.getFirstname());
			myStmt.setString(4, newuser.getLastname());
			myStmt.setString(5, newuser.getEmail());
			
			myStmt.execute();
			checker=true;
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn,myStmt,null);
	
		}
		
		return checker;
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
