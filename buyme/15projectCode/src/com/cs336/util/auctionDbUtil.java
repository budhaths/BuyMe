package com.cs336.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import com.cs336.javafiles.Item;

public class auctionDbUtil {

private DataSource dataSource;
	
	public auctionDbUtil (DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public  List<Item> getAuctionDetails() {
	
		List<Item> auction_item= new ArrayList<>();
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			
			String sql= "select * from Item where sold = false order by item_id";
			
			myStmt= myConn.createStatement();
			
			myRs=myStmt.executeQuery(sql);
			
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
	
	public void updateAfterNewBid(float new_bid, int item_id, String buyer) {
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		Timestamp date = new Timestamp(new java.util.Date().getTime());
		
		if(checkIfBidExists(item_id)==true) {
			
			updateBiddingHistory(item_id);
		
			try {
				
				myConn= dataSource.getConnection();
				myStmt =myConn.prepareStatement
		                		("update Bid set current_bid=?,buyer=?,date=? where item_id=?");
				myStmt.setFloat(1, new_bid);
				myStmt.setString(2, buyer);
				myStmt.setTimestamp(3, date);
				myStmt.setInt(4,item_id);
				myStmt.executeUpdate();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			finally {
			close(myConn, myStmt, myRs);
				}
		}else {//if there is no bid for this item
				try {
				myConn = dataSource.getConnection();
				myStmt = myConn.prepareStatement
		                		("insert into Bid values(?,?,?,?)");
				myStmt.setInt(1,item_id);
				myStmt.setString(2, buyer);
				myStmt.setFloat(3, new_bid);
				myStmt.setTimestamp(4, date);
				myStmt.executeUpdate();
			
			} catch(Exception e) {
				e.printStackTrace();
			}
				finally {
					
					close(myConn, myStmt, myRs);
				}
		}
		updateItemCurrentBid(item_id,new_bid);
			
	}
	
	private void updateItemCurrentBid(int item_id, float new_bid) {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		try {
			myConn = dataSource.getConnection();
			String sql="update Item set price=? where item_id=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setFloat(1, new_bid);
			myStmt.setInt(2, item_id);
			 myStmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	private void updateBiddingHistory(int item_id) {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		try {
			myConn = dataSource.getConnection();
			String sql="select * from Bid where item_id=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1,item_id);
			myRs= myStmt.executeQuery();
			
			while(myRs.next()) {
				int item_id_update= myRs.getInt("item_id");
				String buyer_update = myRs.getString("buyer");
				float bid_update = myRs.getFloat("current_bid");
				String date_update= myRs.getString("date");
				
				updateBiddingHistory_now(item_id_update,buyer_update,bid_update,date_update);
				sendBidAlert(item_id_update,buyer_update,bid_update);
			}
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
	}

	private void sendBidAlert(int item_id_update, String buyer_update, float bid_update) {

		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		String msg= "Your $"+bid_update+" bid in Auction No "+item_id_update+" has been outnumbered.";
		
		try {
			 myConn=dataSource.getConnection();
			 String sql="insert into email (mail_to,mail_from,message) values (?,?,?)";
			 myStmt=myConn.prepareStatement(sql);
			 myStmt.setString(1, buyer_update);
			 myStmt.setString(2, "BuyMe");
			 myStmt.setString(3, msg);
			 myStmt.executeUpdate();
				
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	private void updateBiddingHistory_now(int item_id_update, String buyer_update, float bid_update,
			String date_update) {
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
	
		try {
			myConn = dataSource.getConnection();
			String sql= "insert into BidHistory (item_id,buyer,bid,date) values(?,?,?,?)";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1, item_id_update);
			myStmt.setString(2, buyer_update);
			myStmt.setFloat(3, bid_update);
			myStmt.setString(4, date_update);
			myStmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
		
	}

	private boolean checkIfBidExists(int item_id) {

		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		boolean checker=false;
		try {
			myConn = dataSource.getConnection();
			myStmt = myConn.prepareStatement("select * from Bid where item_id=?");
			myStmt.setInt(1,item_id);
			myRs= myStmt.executeQuery();
			checker=myRs.next();
			
		}catch(Exception e) {
			e.printStackTrace();
		} finally {
			close(myConn, myStmt, myRs);
		}
		return checker;
	}

	public Item getItemDetails(int item_id) {
		
		Connection myConn=null;
		Statement myStmt=null;
		ResultSet myRs =null;
		Item tempitem=null;
		
		try {
			
			myConn =dataSource.getConnection();
			String sql= "select * from Item where item_id= "+item_id+"";
			
			myStmt= myConn.createStatement();
			
			myRs=myStmt.executeQuery(sql);
			
			while(myRs.next()) {
				int itemid = myRs.getInt("item_id");
				String category = myRs.getString("category");
				String brand= myRs.getString("brand");
				String gender= myRs.getString("gender");
				float size = myRs.getFloat("size");
				String model =myRs.getString("model");
				String color = myRs.getString("color");
				String endDate = myRs.getString("end_date");
				String seller = myRs.getString("seller_id");
				float m_p = myRs.getFloat("min_price");
				float c_p=myRs.getFloat("price");
				String startDate= myRs.getString("start_date");
				
			tempitem= new Item(itemid,category,brand,gender,size,model,color,seller,m_p,c_p,startDate,endDate);	
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
		}
		
		return tempitem;
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
