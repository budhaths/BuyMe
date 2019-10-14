package com.cs336.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

import com.cs336.javafiles.User;

/**
 * Servlet implementation class newAuctionController
 */
@WebServlet("/newAuctionController")
public class newAuctionController extends HttpServlet {
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
	    
	    String category = request.getParameter("category");
		String brand = request.getParameter("brand");
		String gender = request.getParameter("gender");
		float size = Float.parseFloat(request.getParameter("size"));		
		String model = request.getParameter("model");
		String color = request.getParameter("color");
		String seller = (session.getAttribute("user")).toString();
		float minPrice = Float.parseFloat(request.getParameter("min_price"));
		String endDate = request.getParameter("end_datetime");
		float pp=0;
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet rs = null;
		
		try {
			myConn=dataSource.getConnection();
			
			String sql ="insert into Item" 
						+"(category,brand,model,gender,size,color,seller_id,min_price,price,sold,start_date,end_date)"
						+ "values(?,?,?,?,?,?,?,?,?,?,?,?)";
			
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, category);
			myStmt.setString(2, brand);
			myStmt.setString(3, model);
			myStmt.setString(4, gender);
			myStmt.setFloat(5, size);
			myStmt.setString(6, color);
			myStmt.setString(7, seller);
			myStmt.setFloat(8, minPrice);
			myStmt.setFloat(9,  pp);
			myStmt.setBoolean(10,false );
			myStmt.setTimestamp(11,  new java.sql.Timestamp(new java.util.Date().getTime()));
			myStmt.setString(12, endDate);
			
			myStmt.execute();
		
			out.println("<script type=\"text/javascript\">");
	    	 out.println("alert('Auction Created');");
	       	 out.println("</script>");
	       	 RequestDispatcher rs1 = request.getRequestDispatcher("MyAccountController");
	       	 rs1.include(request, response);
		
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
