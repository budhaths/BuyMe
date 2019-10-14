package com.cs336.javafiles;

public class BidHistory {
	private int item_id;
	private String buyer;
	float bid;
	String date;
	
	
	public BidHistory(int item_id, String buyer, float bid, String date) {

		this.item_id = item_id;
		this.buyer = buyer;
		this.bid = bid;
		this.date = date;
	}
	
	public BidHistory(String buyer, float bid, String date) {
		this.buyer = buyer;
		this.bid = bid;
		this.date = date;
	}

	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	public float getBid() {
		return bid;
	}
	public void setBid(float bid) {
		this.bid = bid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
}
