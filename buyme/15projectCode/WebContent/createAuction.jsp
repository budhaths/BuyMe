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
<h1> New Auction Creator</h1>
 <form action="newAuctionController" method="POST">
			<label for="category">Category</label> <select name="category" id="category" required>
				<option value="" disabled selected hidden="true">Select category</option>
				<option value="Dress Shoes">Dress Shoes</option>
				<option value="Sneakers">Sneakers</option>
				<option value="Sandals">Sandals</option>
				<option value="Boots">Boots</option>
			</select> <br>
			<label for="brand">Brand</label>
			<select name="brand" id="brand" required>
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
			<input type="radio" name="gender" value="Men's" required> Men's <br> 
			<input type="radio" name="gender" value="Women's"> Women's <br> 
			<input type="radio" name="gender" value="Boys'"> Boys' <br> 
			<input type="radio" name="gender" value="Girls'"> Girls' <br>
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
			
			<label for="model">Model</label>
			<input type="text" name="model" id="model" placeholder="Enter model name of the item" required> <br>
			
			<label for="color">Color</label>
			<input type="text" name="color" id="color" placeholder="Enter the item color" required> <br>
			
			
			<!-- RESTRICT HOURS TO WHOLE HOURS --> 
			<label for="end_datetime">End Date and Time</label>
			<input type="datetime-local" name="end_datetime" id="end_datetime"  required> <br>
			
			<label for="min_price">Minimum Selling Price</label>
			<input type="number" step="0.01" name="min_price" placeholder="0.00" min="0.01" required> <br>	<br>
			
			<input type="submit" value="Create">
			
		</form>
</div>

</body>
</html>
