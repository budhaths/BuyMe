package com.cs336.javafiles;

public class Mail {
	private String mail_to;
	private String mail_from;
	private String message;
	
	public Mail(String mail_to, String mail_from, String message) {
		this.mail_to = mail_to;
		this.mail_from = mail_from;
		this.message = message;
	}
	
	public String getMail_to() {
		return mail_to;
	}
	public void setMail_to(String mail_to) {
		this.mail_to = mail_to;
	}
	public String getMail_from() {
		return mail_from;
	}
	public void setMail_from(String mail_from) {
		this.mail_from = mail_from;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
