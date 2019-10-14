<!DOCTYPE html>
<html>

  <head>
    <title>Register</title>
    <link rel ="stylesheet" href="css/regis.css" />
	<link rel ="stylesheet" href="css/forgotpass.css" />
    <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">

  </head>
<body>


	   <div class="topnav">
  		 <!-- Centered link -->
  			<a href="index.jsp" class="active">Home</a>
    		<a href="login.jsp" class="active">Login</a>
        	<a href="regis.jsp" class="active">Register</a> 	 
	   </div> <!--  end of top nav -->
	   
	   
<div class="form-bar">

<form name ="myForm" method="post" action="ForgotPasswordController" class="formclass">

<div class="box">
	<h1 style="padding-bottom:10px;">Forgot Password?</h1>

	<label for="uname" style="margin-top:10px;">Enter your username:</label><br>
	<input type="text" name="uname" id="uname" class="fieldStyle" required /><br>

	<label for="email">Enter your email: </label><br>
	<input type="text" name="email" id="email" class="fieldStyle" required /><br>

	<input type="submit" id="btn2" value="Email my password" >
 
</div> 
  
</form>
 
</div>

</body>

</html>