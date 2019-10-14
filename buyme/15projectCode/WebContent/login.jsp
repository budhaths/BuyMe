<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

  <head>
    <title>Login</title>
    <link rel ="stylesheet" href="css/login.css" />
    <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
  </head>


<body>

  <div class="form-bar">
	<form method="post" action="LoginController" class="formclass">
		
		<div class="box">
			<h1>BuyMe Login</h1>
			<input type="username" name="username"  class="email" required />
			<input type="password" name="password" class="email" required />
			<input type="submit" class="btn" value="Sign In" /><br>
			<a href="regis.jsp"><div id="btn2">Sign Up</div></a> <!-- End Btn2 -->

		</div> <!-- End Box -->
  	</form>
	
<p>Forgot your password? <a style="color:#f1c40f;" href="forgotpass.jsp">Click Here!</a></p>
  
</div>

</body>
</html>