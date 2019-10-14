package com.cs336.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
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
import com.cs336.javafiles.Mail;

/**
 * Servlet implementation class searchController
 */
@WebServlet("/searchController")
public class searchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_category=request.getParameter("category");
		String search_brand=request.getParameter("brand");
		String search_gender=request.getParameter("gender");
		String search_model=request.getParameter("model");
		String search_color=request.getParameter("color");
		
		if(search_brand!=null &&search_gender!=null &&search_model!=null &&search_color!=null) {
			List<Item> getItem = searchResult(search_category,search_brand,search_gender,search_model,search_color);
				request.setAttribute("search_result", getItem);
				RequestDispatcher dispatcher4= request.getRequestDispatcher("search_result.jsp");
				dispatcher4.forward(request, response);
				
		}else if(search_brand!=null && search_gender!=null&&search_model!=null && search_color==null) {
			List<Item> getItem = searchResult(search_category,search_brand,search_gender,search_model);
				request.setAttribute("search_result", getItem);
				RequestDispatcher dispatcher4= request.getRequestDispatcher("search_result.jsp");
				dispatcher4.forward(request, response);
				
		}else if(search_brand!=null && search_gender!=null&&search_model==null && search_color==null) {
			List<Item> getItem = searchResult(search_category,search_brand,search_gender);
				request.setAttribute("search_result", getItem);
				RequestDispatcher dispatcher4= request.getRequestDispatcher("search_result.jsp");
				dispatcher4.forward(request, response);
				
		}else if(search_brand!=null && search_gender==null&&search_model==null && search_color==null) {
			List<Item> getItem = searchResult(search_category,search_brand);
				request.setAttribute("search_result", getItem);
				RequestDispatcher dispatcher4= request.getRequestDispatcher("search_result.jsp");
				dispatcher4.forward(request, response);
				
		//}else if(search_brand==null && search_gender==null&&search_model==null && search_color==null) {
		}else {		
			List<Item> getItem = searchResult(search_category);
				request.setAttribute("search_result", getItem);
				RequestDispatcher dispatcher4= request.getRequestDispatcher("search_result.jsp");
				dispatcher4.forward(request, response);
		}	
	}
	
	private List<Item> searchResult(String search_category) {
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			String sql= "select * from Item where sold = false and category=? ";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, search_category);
	
			myRs=myStmt.executeQuery();
			
			while(myRs.next()) {
				int item_id = myRs.getInt("item_id");
				String category = myRs.getString("category");
				String brand= myRs.getString("brand");
				String gender= myRs.getString("gender");
				float size = myRs.getFloat("size");
				String model =myRs.getString("model");
				String color = myRs.getString("color");
				String endDate = myRs.getString("end_date");
		
				Item tempItem = new Item(item_id,category,brand, gender,size,model,color,endDate); 
				auction_item.add(tempItem);
					}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		return auction_item;
	}

	private List<Item> searchResult(String search_category, String search_brand) {
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			String sql= "select * from Item where sold = false and category=? and brand=?  ";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, search_category);
			myStmt.setString(2, search_brand);
	
			myRs=myStmt.executeQuery();
			
			while(myRs.next()) {
				int item_id = myRs.getInt("item_id");
				String category = myRs.getString("category");
				String brand= myRs.getString("brand");
				String gender= myRs.getString("gender");
				float size = myRs.getFloat("size");
				String model =myRs.getString("model");
				String color = myRs.getString("color");
				String endDate = myRs.getString("end_date");
		
				Item tempItem = new Item(item_id,category,brand, gender,size,model,color,endDate); 
				auction_item.add(tempItem);
					}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		return auction_item;
	}

	private List<Item> searchResult(String search_category, String search_brand, String search_gender) {
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			String sql= "select * from Item where sold = false and category=? and brand=? and gender=? ";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, search_category);
			myStmt.setString(2, search_brand);
			myStmt.setString(3, search_gender);
			myRs=myStmt.executeQuery();
			
			while(myRs.next()) {
				int item_id = myRs.getInt("item_id");
				String category = myRs.getString("category");
				String brand= myRs.getString("brand");
				String gender= myRs.getString("gender");
				float size = myRs.getFloat("size");
				String model =myRs.getString("model");
				String color = myRs.getString("color");
				String endDate = myRs.getString("end_date");
		
				Item tempItem = new Item(item_id,category,brand, gender,size,model,color,endDate); 
				auction_item.add(tempItem);
					}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		return auction_item;
	}

	private List<Item> searchResult(String search_category, String search_brand, String search_gender,
			String search_model) {
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			String sql= "select * from Item where sold = false and category=? and brand=? and gender=? and model=? ";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, search_category);
			myStmt.setString(2, search_brand);
			myStmt.setString(3, search_gender);
			myStmt.setString(4, search_model);
			myRs=myStmt.executeQuery();
			
			while(myRs.next()) {
				int item_id = myRs.getInt("item_id");
				String category = myRs.getString("category");
				String brand= myRs.getString("brand");
				String gender= myRs.getString("gender");
				float size = myRs.getFloat("size");
				String model =myRs.getString("model");
				String color = myRs.getString("color");
				String endDate = myRs.getString("end_date");
		
				Item tempItem = new Item(item_id,category,brand, gender,size,model,color,endDate); 
				auction_item.add(tempItem);
					}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		return auction_item;
	}

	private List<Item> searchResult(String search_category, String search_brand, String search_gender, String search_model,String search_color){
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			String sql= "select * from Item where sold = false and category=? and brand=? and gender=? and model=? and color=? ";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1, search_category);
			myStmt.setString(2, search_brand);
			myStmt.setString(3, search_gender);
			myStmt.setString(4, search_model);
			myStmt.setString(5, search_color);
			myRs=myStmt.executeQuery();
			
			while(myRs.next()) {
				int item_id = myRs.getInt("item_id");
				String category = myRs.getString("category");
				String brand= myRs.getString("brand");
				String gender= myRs.getString("gender");
				float size = myRs.getFloat("size");
				String model =myRs.getString("model");
				String color = myRs.getString("color");
				String endDate = myRs.getString("end_date");
		
				Item tempItem = new Item(item_id,category,brand, gender,size,model,color,endDate); 
				auction_item.add(tempItem);
					}
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		return auction_item;
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
