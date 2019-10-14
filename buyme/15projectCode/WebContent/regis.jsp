<!DOCTYPE html>
<html>

  <head>
    <title>Register</title>
    <link rel ="stylesheet" href="css/regis.css" />
    <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">

  </head>
<body>


<div class="form-bar">

<form name ="myForm" method="post" action="RegistrationController" class="formclass">

<div class="box">
	<h1>BuyMe Registration</h1>

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

<p>Already a member? <a style="color:#f1c40f;" href="login.jsp">Click Here to login!</a></p>
 
</div>

</body>

</html>