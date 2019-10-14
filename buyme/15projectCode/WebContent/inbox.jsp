<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
  <link rel="stylesheet"  href="css/homepage.css"/>
  <link rel="stylesheet"  href="css/inbox.css"/>
<meta charset="ISO-8859-1">
<title>Inbox</title>
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
      <a href="messageController" style="color:red;">Messages</a>
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

<div class="inbox">
<table>
  <tr>
    <th>From</th>
    <th>Subject</th>
    
  </tr>
  <c:forEach var="mList" items="${mail_ll}">
  <tr>
    <td>${mList.getMail_to()}</td>
    <td>${mList.getMessage()}</td>
  </tr>
  </c:forEach>
</table>
</div>




</body>
</html>