package com.cs336.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public static void send(String to, String subject, String buymepass, String user, String password) {
		// TODO Auto-generated method stub
		//create an instance of Properties Class   
	     Properties props = new Properties();
	     
	     /* Specifies the IP address of your default mail server
	     	   for e.g if you are using gmail server as an email sever
	           you will pass smtp.gmail.com as value of mail.smtp host. 
	           As shown here in the code. 
	           Change accordingly, if your email id is not a gmail id
	        */
	     props.put("mail.smtp.host", "smtp.gmail.com");
	     //below mentioned mail.smtp.port is optional
	     props.put("mail.smtp.port", "587");		
	     props.put("mail.smtp.auth", "true");
	     props.put("mail.smtp.starttls.enable", "true");
	     
	     /* Pass Properties object(props) and Authenticator object   
	           for authentication to Session instance 
	        */

	    Session session = Session.getInstance(props,new javax.mail.Authenticator()
	    {
	  	  protected PasswordAuthentication getPasswordAuthentication() 
	  	  {
	  	 	 return new PasswordAuthentication(user,password);
	  	  }
	   });
	    
	   try
	   {
	 
	 	/* Create an instance of MimeMessage, 
	 	      it accept MIME types and headers 
	 	   */
	 
	    MimeMessage message = new MimeMessage(session);
	       message.setFrom(new InternetAddress(user));
	       message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
	       message.setSubject(subject);
	       message.setText(buymepass);

	       /* Transport class is used to deliver the message to the recipients */
	       
	       Transport.send(message);
	 
	 
	    }
	    catch(Exception e)
	    {
	    	 e.printStackTrace();
	    }
	}

}
