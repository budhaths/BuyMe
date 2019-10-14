package com.cs336.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import com.cs336.javafiles.BidHistory;
import com.cs336.javafiles.Item;

public class autoBidUtil {

private DataSource dataSource;
	
	public autoBidUtil (DataSource dataSource) {
		this.dataSource=dataSource;
	}

	public void setNewAutoBid(String user_name, int item_id, float auto_bid) {
		
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		try {
			
			myConn =dataSource.getConnection();
			String sql= "insert into AutoBidding values(?,?,?)";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1,user_name);
			myStmt.setInt(2,item_id);
			myStmt.setFloat(3, auto_bid);
			myStmt.executeUpdate();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			close(myConn, myStmt, myRs);
		}	
	}
	
public void deleteAutoBid(int item_id, String auto_bid_buyer_name) {
	Connection myConn=null;
	PreparedStatement myStmt=null;
	ResultSet myRs =null;
	
	try {
		
		myConn =dataSource.getConnection();
		String sql= "delete from AutoBidding where item_id=? and user_name=?";
		myStmt=myConn.prepareStatement(sql);
		myStmt.setInt(1,item_id);
		myStmt.setString(2, auto_bid_buyer_name);
		myStmt.execute();
		
	} catch(Exception e) {
		e.printStackTrace();
	}
	finally {
		close(myConn, myStmt, myRs);
	}	
		
	}

	public List<BidHistory> getBidHistory(int item_id) {
	
		List<BidHistory> bidhistory_list= new LinkedList<BidHistory>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet myRs =null;
		
		String buyer=null;
		float bid=0;
		String date=null;
		
		try {
			myConn=dataSource.getConnection();
			String sql="select * from BidHistory where item_id=?";
			myStmt=myConn.prepareStatement(sql);
			myStmt.setInt(1, item_id);
			myRs=myStmt.executeQuery();
			
			while(myRs.next()) {
				buyer=myRs.getString("buyer");
				bid=myRs.getFloat("bid");
				date=myRs.getString("date");
				
				BidHistory temp= new BidHistory(buyer,bid,date);
				bidhistory_list.add(temp);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		  finally {
			  close(myConn, myStmt, myRs);
		  }
		
		return bidhistory_list;
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
