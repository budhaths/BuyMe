<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/support.css"/>
<link rel="stylesheet"  href="css/homepage.css"/>
<link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
<title>contact us</title>
</head>
<body>
<%
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
}%>
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
  <a href="support.jsp" style="color:red;">Contact Support</a>
</div>

<div class="container">
  <div style="text-align:center">
    <h2>Contact Us</h2>
    <p>Send us an message if you have any questions about any auctions, <br>click <a href="changepassword.jsp">here</a> to change your password.
     Click <a href="deleteaccount.jsp">here</a> to delete your account.
    </p>

  </div>
  <div class="row">
   
    <div class="column">
      <form method="post" action="supportController" >
      <input type="hidden" name="command" value="supportmessage">
        <label for="fname">Your UserName</label>
        <input type="text" id="fname" name="username" placeholder="Your username..">
        <label for="lname">Your Email</label>
        <input type="text" id="lname" name="email" placeholder="Your email..">
        <label for="subject">Subject</label>
        <textarea id="subject" name="subject" placeholder="Write something.." style="height:170px"></textarea>
        <input type="submit" value="Submit">
      </form>
    </div>
  </div>
 
  <table>
  <tr>
    <th>Questions and Answers</th>
  </tr>
  <tr>
    <td>Questions: How can I the list of auctions I put?</td>
  </tr>
  <tr>
    <td>Answer: You can click <a href="my_auctionController">here</a> to view your auctions.</td>
  </tr>
  <tr>
    <td>Questions: How can I set alerts for items I want to buy in future?</td>
  </tr>
  <tr>
    <td>Answer: You can set alerts through <a href="setAlerts.jsp">here</a></td>
  </tr>
  <tr>
    <td>Questions: How can I search for particular auctions?</td>
  </tr>
  <tr>
    <td>Answer: You can search auction through <a href="searchAuction.jsp">here</a></td>
  </tr>
  
</table>
  
</div>
</body>
</html>