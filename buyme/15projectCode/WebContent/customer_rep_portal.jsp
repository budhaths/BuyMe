<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ page import="java.io.*,java.util.*,java.sql.*,java.text.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet"  href="css/homepage.css"/>
  <link rel="stylesheet"  href="css/inbox.css"/>
  <link rel="stylesheet" href="css/support.css"/>
   <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
<meta charset="ISO-8859-1">
<title>Customer Representative Portal</title>
</head>
<body>

<% String str= session.getAttribute("user").toString();
  	boolean checker=false;
  	
  if(session.getAttribute("user") == null) {
    		response.sendRedirect("login.jsp");
       } 
  
  String url = "jdbc:mysql://fp2016.cryo3le37rkt.us-east-2.rds.amazonaws.com:3306/cs336db";
	Connection conn = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	try{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn = DriverManager.getConnection(url, "cs336", "cs336cs336");
		String sql="select user_name from customer_rep where user_name=?";
		ps=conn.prepareStatement(sql);
		ps.setString(1,str);
		rs=ps.executeQuery();
		if(rs.next()){
			checker=true;
		}
		
		if(checker==false){
			response.sendRedirect("MyAccountController");
		}
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
		try { rs.close(); } catch (Exception e) {}
		try { ps.close(); } catch (Exception e) {}
        try { conn.close(); } catch (Exception e) {}
	}
  %>
    	   	
<div class="topnav">
  <div class="dropdown">
    <button class="dropbtn">My Account
      <i class="fa fa-caret-down"></i>
    </button>
    <div class="dropdown-content">
      <a href="messageController">Messages</a>
      <a href="my_auctionController">My Auctions</a>
      <a href="admin_portal.jsp">Admin Portal</a> 
      <a href="customerRepHandler">Customer Rep Portal</a>
      
      <a href="changepassword.jsp">Change Password</a>
       <a href="deleteaccount.jsp">Delete Account</a>
        <a href="logout.jsp">Logout</a>
    </div>
  </div> 
  <a href="MyAccountController" >Home</a>
  <a href="createAuction.jsp">Create Auction</a>
  <a href="searchAuction.jsp">Search Auction</a>
  <a href="setAlerts.jsp">Set Alerts</a>
  <a href="support.jsp" >Contact Support</a>
</div>
<br>
<h3>Customer's Messages</h3><br>
<div class="inbox">
<table>
  <tr>
    <th>From</th>
    <th>Subject</th>
    
  </tr>
  <c:forEach var="mList" items="${mail_ll}">
  <tr>
    <td>${mList.getMail_from()}</td>
    <td>${mList.getMessage()}</td>
    <td>
  </tr>
  </c:forEach>
</table>
</div>
<br><br>
<h3>Reply to the user</h3>
<div class="row" id="121" >
   
    <div class="column">
      <form method="post" action="supportController" >
      <input type="hidden" name="command" value="reply">
        <label for="fname">Reply to</label>
        <input type="text" id="fname" name="username" placeholder="User Name">
      
        <label for="subject">Subject</label>
        <textarea id="subject" name="subject" placeholder="Write something.." style="height:170px"></textarea>
        <input type="submit" value="Submit">
      </form>
    </div>
  </div>
  
  <h3>Auto Bid Delete</h3> 
  
 <div class="row" >
 <div class="column">
<form name="autoBid" action="showAuctionController" method="get" >
<input type="hidden" name="command" value="deleteautoBid"/>
Auto Bid User Name: <input type="text" name="user_name" required/>
Auto Bid Auction No: <input type="number" name="autobid_number" required/>
<input type="submit" value="Delete auto bid">
</form>
</div></div>

 <h3>Delete Auction</h3>
 <div class="row" >
 <div class="column">
<form name="autoBid" action="my_auctionController" method="post" >
<input type="hidden" name="command" value="delete"/>
Auction Number: <input type="number" name="item_id_1" required/><br><br>
<input type="submit" value="Delete Auction">
</form>
</div></div>

</body>
</html>