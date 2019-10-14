<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>
BuyMe
</title>
  	    <link rel="stylesheet"  href="css/homepage.css"/>
  	    <link rel="stylesheet"  href="css/admin_portal.css"/>
  	          <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
</head>
<body>

 <% String str= session.getAttribute("user").toString();
  	
  if(session.getAttribute("user") == null) {
    		response.sendRedirect("login.jsp");
       } else if(str.equals("admin")==false){ 
    	   out.println("<script type=\"text/javascript\">");
  	       out.println("alert('Access denied to admin portal');");
  	       out.println("</script>");
  	     response.sendRedirect("MyAccountController");
       }
    	  
    	   	String reportType = request.getParameter("type"); %>
  
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

<div class="adminportal">
<h2>Select a sales report to generate</h2>

	    	<ul>
	            <li><a href="salesReport.jsp?type=totalEarnings">Total Earnings</a></li>
	            <li><a href="salesReport.jsp?type=earningsPerItem">Earnings per item</a></li>
	            <li><a href="salesReport.jsp?type=earningsPerItemType">Earnings per item type</a></li>
	            <li><a href="salesReport.jsp?type=earningsPerEndUser">Earnings per end-user</a></li>
	            <li><a href="salesReport.jsp?type=bestSelling">Best-selling items</a></li>
	            <li><a href="salesReport.jsp?type=bestBuyers">Best buyers</a></li>	            
	    	</ul>

<div class="form-bar">

<form name ="myForm" method="post" action="csrep_regisController" class="formclass">

<div class="box">
	<h1>New Customer Representative</h1>
<input type="hidden" name="type" value="customer_rep" > 
	<label for="fname">First Name</label><br>
	<input type="text" name="fname" id="fname" class="fieldStyle" required /><br>

	<label for="lname">Last Name</label><br>
	<input type="text" name="lname" id="lname" class="fieldStyle" required /><br>

	<label for="email">Email</label><br>
	<input type="email" name="email" id="email" class="fieldStyle" required /><br>

	<label for="uname">User Name</label><br>
	<input type="text" name="username" id="uname" class="fieldStyle" required /><br>

	<label for="pass">Password</label><br>
	<input type="text" name="password" id="pass" class="fieldStyle" required /><br>

	<input type="submit" id="btn2" value="Sign Up" >
 
</div> 
  
</form>

 
</div>

</body>
</html>