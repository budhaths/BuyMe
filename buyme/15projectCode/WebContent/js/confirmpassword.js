function checkpassword(){
	  var password = document.getElementById("pass").value;
      var confirmPassword = document.getElementById("cpass").value;
      if (password != confirmPassword) {
          alert("Passwords do not match.");
          return false;
      }else{
    	   alert("Passwords match.");
      }
      return true;
  }
	
	
