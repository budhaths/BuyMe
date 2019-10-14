<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<title>
BuyMe
</title>
  	    <link rel="stylesheet"  href="css/homepage.css"/>
  	      <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
</head>
<body>

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
  <a href="MyAccountController" style="color:red;">Home</a>
  <a href="createAuction.jsp">Create Auction</a>
  <a href="searchAuction.jsp">Search Auction</a>
  <a href="setAlerts.jsp">Set Alerts</a>
  <a href="support.jsp">Contact Support</a>
</div>

<!-- end of top navigation bar -->
<%
//allow access only if session exists
String user = null;
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
}else user = (String) session.getAttribute("user");
String userName = null;
String sessionID = null;
Cookie[] cookies = request.getCookies();
if(cookies !=null){
for(Cookie cookie : cookies){
	if(cookie.getName().equals("user")) userName = cookie.getValue();
	if(cookie.getName().equals("JSESSIONID")) sessionID = cookie.getValue();
}
}else{
	sessionID = session.getId();
}
%>
<br> <br>
<h3>Welcome to BuyMe : <%=user %> </h3>
<br>
<h4>  Live Auctions</h4>
<div style="overflow-x:auto;">
  <table>
    <tr>
      <th>Auction No.</th>
      <th>Category</th>
      <th>Model</th>
      <th>Brand</th>
      <th>Gender</th>
      <th>Size</th>
      <th>Color</th>
      <th>End Date</th>
      <th>Auction</th> 
    </tr>
    
    
    
    <c:forEach var="auction" items="${AUCTIONLIST}">
    
     <c:url var="deleteLink" value="showAuctionController">
  	 	<c:param name="command" value="show_particular_auction"/>
  	 	<c:param name="item_id" value="${auction.getItem_id()}"/>
  	 </c:url>
  	 
    <tr>
    	<th>${auction.getItem_id()}</th>
    	<th>${auction.getCategory()}</th>
   		<th>${auction.getModel()}</th>
   		<th>${auction.getBrand()}</th>
   		<th>${auction.getGender()} </th>
   		<th>${auction.getSize()} </th>
   		<th>${auction.getColor() } </th>
   		<th>${auction.getEndDate()} </th>
   		<th><a href="${deleteLink}">Click Here </a> </th>
	<tr>    
    
    </c:forEach>
    
    
  </table>
</div>
</body>
</html>