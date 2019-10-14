<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>
Change Password
</title>
  	    <link rel="stylesheet"  href="css/nav-bar.css"/>
  	    <link rel="stylesheet" href="css/deleteaccount.css"/>
  	      <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
</head>
<body>

<%
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
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
<!-- end of top navigation bar -->
<!-- delete account -->
<div class="container">
   <form method="post" action="MyAccountController">
  <input type="hidden" name="command" value="changepassword" />
    <div class="row">
      <div class="col-25">
        <label for="email">Enter your email</label>
      </div>
      <div class="col-75">
        <input type="text" id="email" name="email" placeholder="Your email.." required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="cpass">Enter your current password</label>
      </div>
      <div class="col-75">
        <input type="text" id="cpass" name="cpass" placeholder="Your current password.." required>
      </div>
    </div>
    
    <div class="row">
      <div class="col-25">
        <label for="npass">Enter your new password</label>
      </div>
      <div class="col-75">
        <input type="text" id="npass" name="npass" placeholder="Your new password.." required>
      </div>
    </div>
    
    <div class="row">
      <input type="submit"  value="Change Password">
    </div>
  </form>
  
</div>
</body>
</html>
