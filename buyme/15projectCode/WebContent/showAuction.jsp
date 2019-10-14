<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="css/showAuction.css" />
<link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
<title>Auction</title>
<script type="text/javascript" >

function validateForm1() {
	var user_name=document.getElementById("99").value;
	var user_name_loggedin = '<%= session.getAttribute("user") %>';
	if(user_name.toLowerCase()==user_name_loggedin.toLowerCase()){
		alert("You cannot bid on your own item");
		return false;
	}	
	var min_bid=document.getElementById("21").value;
	var current_bid=document.getElementById("22").value;
	var new_bid=parseFloat(document.getElementById("14").value);

	if(new_bid<0){
		alert("No negative bid allowed");
		return false;
	}
	if(new_bid>1000000){
		alert("Please give a resonable bid");
		return false;
	}
	if( new_bid < current_bid){
		alert("Your bid should be greater than current bid");
		return false;
	}
}

function validateForm2() {
	var user_name=document.getElementById("98").value;
	var user_name_loggedin = '<%= session.getAttribute("user") %>';
	
	if(user_name.toLowerCase()==user_name_loggedin.toLowerCase()){
		alert("You cannot bid on your own item");
		return false;
	}	
	
	var current_bid=document.getElementById("22").value;
	var auto_bid= parseFloat(document.getElementById("16").value);
	
	if(auto_bid<0){
		alert("No negative bid allowed");
		return false;
	}
	if(auto_bid<current_bid){
		alert("Your auto max bid should be higher than current bid on item");
		return false;
	}
}
</script>
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
  <a href="support.jsp">Contact Support</a>
</div>

<h3>Auction ID: <%=request.getAttribute("itemid") %></h3>
<div class="container">
<form>

<label for="0">Seller ID : </label>
<input type="text" name="seller" id="0" value ="${SHOWITEM.seller}" readonly><br>
<label for="1">Category :</label>
<input type="text" name="category" id="1" value="${SHOWITEM.category}"  readonly> <br>

<label for="2">Brand : </label>
<input type="text" name="brand" id="2" value ="${SHOWITEM.brand}" readonly><br>

<label for="3">Model :</label>
<input type="text" name="model" id="3" value="${SHOWITEM.model}" readonly><br>

<label for="4"> Gender :</label>
<input type="text" name="gender" id="4" value="${SHOWITEM.gender}" readonly><br>

<label for="5"> Size :</label> 
<input type="text" name="size" id="5" value="${SHOWITEM.size}" readonly> <br>

<label for="6">Color :</label> 
<input type="text" name="color" id="6" value="${SHOWITEM.color}" readonly> <br>

<label for="7">Start Date :</label>
<input type="text" name="start_date" id="7" value="${SHOWITEM.startDate}" readonly> <br>

<label for="8">End Date :</label> 
<input type="text" name="end_date" id="8" value="${SHOWITEM.endDate}" readonly> <br>

<label for="10">Current bid:</label>
<input type="text" name="current_price" id="10" value="${SHOWITEM.current_price}" readonly> <br>
</form>

<form name="newBid" action="showAuctionController" method ="get">
<input type="hidden" name="command" value="new_bid_submit" />
<input type="hidden" name="item_id"  value="${SHOWITEM.item_id}" />
<input type="hidden" name="seller_user_name" id="99" value="${SHOWITEM.seller}" />
<input type="hidden" name="newbid_min_price" id="21" value="${SHOWITEM.minPrice}" >
<input type="hidden" name="newbid_current_price" id="22" value="${SHOWITEM.current_price}"> 
<input type="hidden" name="start_date" id="start_date_newbid" value="${SHOWITEM.startDate}" > 
<label for="11">Place a new Bid:</label>
<input type="text" name="new_bid" id="14" placeholder="0.00" required>
<input type="submit" value ="submit" onClick="return validateForm1();"> 
</form>

<form name="autoBid" action="showAuctionController" method="get" >
<input type="hidden" name="command" value="auto_bidding_submit"/>
<input type="hidden" name="item_id"  value="${SHOWITEM.item_id}" />
<input type="hidden" name="seller_user_name"  id="98" value="${SHOWITEM.seller}" />
<input type="hidden" name="start_date" id="start_date_autobid" value="${SHOWITEM.startDate}" >
<input type="hidden" name="newbid_current_price" id="22" value="${SHOWITEM.current_price}"> 
<label for="12">Place a new auto bid</label>
<input type="text" name="new_auto_bid" id="16" placeholder="0.00"  required>
<input type="submit" value ="submit" onClick="return validateForm2();" > 
</form>

<form name="similaritems" action="similar_auctionController" method = "post">
<input type="hidden" name="similar_category" value="${SHOWITEM.category}" >
<input type="hidden" name="similar_item_id"  value="${SHOWITEM.item_id}" /> 
<input type="submit" value="Show Similar Items" >
</form>
</div>

<h5>Bid History</h5>

<div class ="bidhistory" style="overflow-x:auto;">
  <table>
    <tr>
      <th scope="col">Bidder</th>
      <th scope="col">Bid Amount</th>
      <th scope="col">Date</th>
    </tr>
    
    <c:forEach var="bid" items="${BHL}">
    
     <tr>
     <td data-label="Bidder">${bid.getBuyer() }</td>
     <td data-label="Bid Amount">${bid.getBid()}</td>
     <td data-label="Date">${bid.getDate()}</td>
     </tr>
      
     </c:forEach>    
  </table>
</div>

</body>
</html>