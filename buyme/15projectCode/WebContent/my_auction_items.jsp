<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>
BuyMe
</title>
  	    <link rel="stylesheet"  href="css/homepage.css"/>
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
  <a href="support.jsp" >Contact Support</a>
</div>
<!-- end of top navigation bar -->

<br> 
<h4>  My Auctions</h4><br>
<div style="overflow-x:auto;">
  <table>
    <tr>
      <th>Category</th>
      <th>Model</th>
      <th>Brand</th>
      <th>Gender</th>
      <th>Size</th>
      <th>Color</th>
      <th>End Date</th>
      <th>Auction</th> 
      <th>Delete</th> 
      
    </tr>
    
    <c:forEach var="auction" items="${myauction_list}">
    
     <c:url var="clickLink" value="showAuctionController">
  	 	<c:param name="command" value="show_particular_auction"/>
  	 	<c:param name="item_id" value="${auction.getItem_id()}"/>
  	 </c:url>
  	 
     <c:url var="deleteLink" value="my_auctionController">
  	 	<c:param name="command" value="delete"/>
  	 	<c:param name="item_id_1" value="${auction.getItem_id()}"/>
  	 </c:url>
  	 
    <tr>
    	<th>${auction.getCategory()}</th>
   		<th>${auction.getModel()}</th>
   		<th>${auction.getBrand()}</th>
   		<th>${auction.getGender()} </th>
   		<th>${auction.getSize()} </th>
   		<th>${auction.getColor() } </th>
   		<th>${auction.getEndDate()} </th>
   			<th><a href="${clickLink}">Click</a> </th>
   		<th><a href="${deleteLink}" onclick="if(!(confirm('Are you sure you want to delete this item?'))) return false">Delete</a> </th>
	<tr>    
    
    </c:forEach>
    
  </table>
</div>
</body>
</html>