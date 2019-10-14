package com.cs336.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.cs336.javafiles.Item;
import javax.sql.DataSource;

/**
 * Servlet implementation class similar_auctionController
 */
@WebServlet("/similar_auctionController")
public class similar_auctionController extends HttpServlet {
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	String category= request.getParameter("similar_category");
	int current_id= Integer.parseInt(request.getParameter("similar_item_id"));
	List<Item> itemList= getSimilarItemList(category,current_id);
	request.setAttribute("similar_item", itemList);
	RequestDispatcher dispatcher6= request.getRequestDispatcher("similar_items.jsp");
	dispatcher6.forward(request, response);
	
	}

	private List<Item> getSimilarItemList(String category,int current_id) {
		
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn = dataSource.getConnection();
			String sql= "select * from Item category=? and item_id!=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1,category);
			myStmt.setInt(2,current_id);
			myRs=myStmt.executeQuery();
			
			while(myRs.next()) {
				int item_id = myRs.getInt("item_id");
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
