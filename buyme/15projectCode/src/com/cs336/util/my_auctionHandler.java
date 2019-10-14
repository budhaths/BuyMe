package com.cs336.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.cs336.javafiles.Item;



public class my_auctionHandler {

private DataSource dataSource;
	
	public my_auctionHandler(DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public List<Item> showMyAuctions(String user_name) {
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			
			String sql= "select * from Item where sold = ? and seller_id=? ";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1,"false");
			myStmt.setString(2,user_name);
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
	
	public void deleteItem(int item_id) {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn=dataSource.getConnection();
			String sql="delete from Item where item_id=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1, item_id);
			myStmt.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
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
