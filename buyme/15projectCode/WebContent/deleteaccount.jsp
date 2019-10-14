<!DOCTYPE html>
<html>
<head>
<title>
Delete Account
</title>
  	    <link rel="stylesheet"  href="css/nav-bar.css"/>
  	    <link rel="stylesheet" href="css/deleteaccount.css"/>
  	      <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
</head>
<body>
<%
String str= session.getAttribute("user").toString();
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
}else if(str.equals("admin")==true){ 
	response.sendRedirect("MyAccountController");
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
    <div class="row">
      <div class="col-25">
      <input type="hidden" name="command" value="deleteaccount" />
        <label for="email">Enter your email</label>
      </div>
      <div class="col-75">
        <input type="text" id="email" name="email" placeholder="Your email.." required>
      </div>
    </div>
    <div class="row">
      <div class="col-25">
        <label for="uname">Enter your username</label>
      </div>
      <div class="col-75">
        <input type="text" id="uname" name="uname" placeholder="Your user name.." required>
      </div>
    </div>
    
    <div class="row">
      <div class="col-25">
        <label for="pass">Enter your password</label>
      </div>
      <div class="col-75">
        <input type="text" id="pass" name="pass" placeholder="Your password.." required>
      </div>
    </div>
    
    <div class="row">
      <input type="submit" onclick="return myFunction();" value="Delete Account">
    </div>
  </form>
</div>

<script type='text/javascript'>
		function myFunction() {
	 		 if (confirm("Are you sure you want to delete your account?")) {
	   	 		return true;
			  } else {
	    		return false;
	 					 }
		}
</script>

</body>
</html>
