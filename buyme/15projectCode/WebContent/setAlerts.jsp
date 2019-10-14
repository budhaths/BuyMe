<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Set Alerts</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
 <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
<link rel="stylesheet" href="css/createAuction.css"/>
</head>

<body>
<%
if(session.getAttribute("user") == null){
	response.sendRedirect("login.jsp");
}%>
<div class="container">
<a href="MyAccountController"> Back to HomePage</a>
<h1> Set New Item Alerts</h1>
 <form action="setAlertController" method="POST">
			<label for="category">Category</label> <select name="category" id="category" required>
				<option value="" disabled selected hidden="true">Select category</option>
				<option value="Dress Shoes">Dress Shoes</option>
				<option value="Sneakers">Sneakers</option>
				<option value="Sandals">Sandals</option>
				<option value="Boots">Boots</option>
			</select> <br>
			
			
			<label for="size">Size</label> 
			<select name="size" id="size" required>
				<option value="" disabled selected hidden="true">Select	size</option>
				<option value="4">4</option>
				<option value="4.5">4.5</option>
				<option value="5">5</option>
				<option value="5.5">5.5</option>
				<option value="6">6</option>
				<option value="6.5">6.5</option>
				<option value="7">7</option>
				<option value="7.5">7.5</option>
				<option value="8">8</option>
				<option value="8.5">8.5</option>
				<option value="9">9</option>
				<option value="9.5">9.5</option>
				<option value="10">10</option>
				<option value="10.5">10.5</option>
				<option value="11">11</option>
				<option value="11.5">11.5</option>
				<option value="12">12</option>
				<option value="12.5">12.5</option>
				<option value="13">13</option>
			</select> <br> 
			
			<label for="color">Color</label>
			<input type="text" name="color" id="color" placeholder="Enter the item color"> <br>
			
			<input type="submit" value="Set Alerts">
			
		</form>
</div>

</body>
</html>