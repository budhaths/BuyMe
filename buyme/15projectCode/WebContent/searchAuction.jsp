<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<h1> Search Auction</h1>
 <form action="searchController" method="POST">
			<label for="category">Category</label> <select name="category" id="category" required>
				<option value="" disabled selected hidden="true">Select category</option>
				<option value="Dress Shoes">Dress Shoes</option>
				<option value="Sneakers">Sneakers</option>
				<option value="Sandals">Sandals</option>
				<option value="Boots">Boots</option>
			</select> <br>
			<label for="brand">Brand</label>
			<select name="brand" id="brand" >
				<option value="" disabled selected hidden="true">Select brand</option>
				<option value="Adidas">Adidas</option>
				<option value="American Eagle">American Eagle</option>
				<option value="Andrew Marc">Andrew Marc</option>								
				<option value="Asics">Asics</option>
				<option value="Clarks">Clarks</option>
				<option value="Cole Haan">Cole Haan</option>
				<option value="Converse">Converse</option>
				<option value="Dr. Martens">Dr. Martens</option>
				<option value="Ecco">Ecco</option>
				<option value="Florsheim">Florsheim</option>
				<option value="J.Crew">J.Crew</option>
				<option value="Kenneth Cole">Kenneth Cole</option>
				<option value="New Balance">New Balance</option>
				<option value="Nike">Nike</option>
				<option value="Puma">Puma</option>
				<option value="Red Wing">Red Wing</option>
				<option value="Reebok">Reebok</option>
				<option value="Steve Madden">Steve Madden</option>
				<option value="Timberland">Timberland</option>
				<option value="UGG">UGG</option>
				<option value="Vans">Vans</option>
				<option value="Other">Other</option>
			</select> <br> 
			<input type="radio" name="gender" value="Men's" > Men's <br> 
			<input type="radio" name="gender" value="Women's"> Women's <br> 
			<input type="radio" name="gender" value="Boys'"> Boys' <br> 
			<input type="radio" name="gender" value="Girls'"> Girls' <br>
			
			<label for="model">Model</label>
			<input type="text" name="model" id="model" placeholder="Enter model name of the item" > <br>
			
			<label for="color">Color</label>
			<input type="text" name="color" id="color" placeholder="Enter the item color"> <br>
			
			<input type="submit" value="Search">
			
		</form>
</div>

</body>
</html>