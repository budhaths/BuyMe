<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.*,java.util.*,java.sql.*,java.text.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>BuyMe Sales Report</title>
    <link href="https://fonts.googleapis.com/css?family=Underdog" rel="stylesheet">
<style>
* {
	font-family: 'Underdog', cursive;
  box-sizing: border-box;
}
table {
  border-collapse: collapse;
  border-spacing: 0;
  width: 100%;
  border: 1px solid #ddd;
}

th, td {
  text-align: left;
  padding: 8px;
}

tr:nth-child(even){background-color: #f2f2f2}
</style>
</head>
<body>

  <% String str= session.getAttribute("user").toString();
  	
  if(session.getAttribute("user") == null) {
    		response.sendRedirect("login.jsp");
       } else if(str.equals("admin")==false){ 
    	   out.println("<script type=\"text/javascript\">");
  	       out.println("alert('Access denied to admin portal');");
  	       out.println("</script>");
  	     response.sendRedirect("MyAccountController");
       }
    	  
    	   	
    	   	String reportType = request.getParameter("type"); %>

<div class="content">
	    <%	
	    String url = "jdbc:mysql://fp2016.cryo3le37rkt.us-east-2.rds.amazonaws.com:3306/cs336db";
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			Locale locale = new Locale("en", "US");
			NumberFormat currency = NumberFormat.getCurrencyInstance(locale);
			
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				conn = DriverManager.getConnection(url, "cs336", "cs336cs336");
				
				String query = null;
		    	if (reportType.equals("totalEarnings")) {
		    		query = "SELECT SUM(price) FROM Item WHERE sold=true";
		    		ps = conn.prepareStatement(query);
		    		rs = ps.executeQuery();
		    		if (rs.next()) { %>
		    			<h2>Sales Report:</h2>
		    			<table>
		    				<tr>
		    					<th>Total Earnings</th>
		    				</tr>	
		    		<%	do { %>
		    				<tr>
		    					<td><%= currency.format(rs.getDouble("SUM(price)")) %></td>
		    				</tr>
		    		<%	} while (rs.next()); %>
		    			</table>
		    		<br>Need more sales reports. Click <a href="admin_portal.jsp">here.</a>
		    		<br>Register new customer representative. Click<a href="admin_portal.jsp">here.</a>
		    		<br>Back to homepage. Click <a href="MyAuctionController">here.</a>
		    		
		    		
		    		
		    	<%	}		    		
		    	} else if (reportType.equals("earningsPerItem")) {
		    		query = "SELECT model, SUM(price) FROM Item WHERE sold=true GROUP BY model";
		    		ps = conn.prepareStatement(query);
		    		rs = ps.executeQuery();
		    		if (rs.next()) { %>
		    			<h2>Sales Report:</h2>
		    			<table>
		    				<tr>
		    					<th>Model</th>
		    					<th>Earnings</th>
		    				</tr>
		    		<%	do { %>
		    				<tr>
		    					<td><%= rs.getString("model") %></td>
		    					<td><%= currency.format(rs.getDouble("SUM(price)")) %></td>
		    				</tr>
		    		<%	} while (rs.next()); %>
		    		</table>
		    		<br>Need more sales reports. Click <a href="admin_portal.jsp">here.</a>
		    		<br>Register new customer representative. Click<a href="admin_portal.jsp">here.</a>
		    		<br>Back to homepage. Click <a href="MyAuctionController">here.</a>
		    	<%	}
		    	} else if (reportType.equals("earningsPerItemType")) {
		    		query = "SELECT category, SUM(price) FROM Item WHERE sold=true GROUP BY category";
		    		ps = conn.prepareStatement(query);
		    		rs = ps.executeQuery();
		    		if (rs.next()) { %>
		    			<h2>Sales Report:</h2>
		    			<table>
		    				<tr>
		    					<th>Category</th>
		    					<th>Earnings</th>
		    				</tr>
		    		<%	do { %>
		    				<tr>
		    					<td><%= rs.getString("category") %></td>
		    					<td><%= currency.format(rs.getDouble("SUM(price)")) %></td>
		    				</tr>
		    		<%	} while (rs.next()); %>
		    		</table>
		    		<br>Need more sales reports. Click <a href="admin_portal.jsp">here.</a>
		    		<br>Register new customer representative. Click<a href="admin_portal.jsp">here.</a>
		    		<br>Back to homepage. Click <a href="MyAuctionController">here.</a>
		    	<%	}
		    	} else if (reportType.equals("earningsPerEndUser")) {
		    		query = "SELECT seller_id, SUM(price) FROM Item WHERE sold=true GROUP BY seller_id";
		    		ps = conn.prepareStatement(query);
		    		rs = ps.executeQuery();
		    		if (rs.next()) { %>
		    			<h2>Sales Report:</h2>
		    			<table>
		    				<tr>
		    					<th>User</th>
		    					<th>Earnings</th>
		    				</tr>
		    		<%	do { %>
		    				<tr>
		    					<td><%= rs.getString("seller_id") %></td>
		    					<td><%= currency.format(rs.getDouble("SUM(price)")) %></td>
		    				</tr>
		    		<%	} while (rs.next()); %>
		    		</table>
		    		<br>Need more sales reports. Click <a href="admin_portal.jsp">here.</a>
		    		<br>Register new customer representative. Click<a href="admin_portal.jsp">here.</a>
		    		<br>Back to homepage. Click <a href="MyAuctionController">here.</a>
		    	<%	}
		    	} else if (reportType.equals("bestSelling")) {
		    		query = "SELECT model, COUNT(model), SUM(price) FROM Item WHERE sold=true GROUP BY model ORDER BY COUNT(model) DESC";
		    		ps = conn.prepareStatement(query);
		    		rs = ps.executeQuery();
		    		if (rs.next()) { %>
		    			<h2>Sales Report:</h2>
		    			<table>
		    				<tr>
		    					<th>Model</th>
		    					<th>Number Sold</th>
		    					<th>Earnings</th>
		    				</tr>
		    		<%	do { %>
		    				<tr>
		    					<td><%= rs.getString("model") %></td>
		    					<td><%= rs.getInt("COUNT(model)") %></td>
		    					<td><%= currency.format(rs.getDouble("SUM(price)")) %></td>
		    				</tr>
		    		<%	} while (rs.next()); %>
		    		</table>
		    		<br>Need more sales reports. Click <a href="admin_portal.jsp">here.</a>
		    		<br>Register new customer representative. Click<a href="admin_portal.jsp">here.</a>
		    		<br>Back to homepage. Click <a href="MyAuctionController">here.</a>
		    	<%	}
		    	} else if (reportType.equals("bestBuyers")) {
		    		query = "SELECT buyer, COUNT(buyer), SUM(price) FROM BuyingHistory GROUP BY buyer ORDER BY COUNT(buyer) DESC";
		    		ps = conn.prepareStatement(query);
		    		rs = ps.executeQuery();
		    		if (rs.next()) { %>
		    			<h2>Sales Report:</h2>
		    			<table>
		    				<tr>
		    					<th>Buyer</th>
		    					<th>Number of purchases</th>
		    					<th>Total amount spent</th>
		    				</tr>
		    		<%	do { %>
		    				<tr>
		    					<td><%= rs.getString("buyer") %></td>
		    					<td><%= rs.getInt("COUNT(buyer)") %></td>
		    					<td><%= currency.format(rs.getDouble("SUM(price)")) %></td>
		    				</tr>
		    		<%	} while (rs.next()); %>
		    		</table>
		    	<br>Need more sales reports. Click <a href="admin_portal.jsp">here.</a>
		    		<br>Register new customer representative. Click <a href="admin_portal.jsp">here.</a>
		    		<br>Back to homepage. Click <a href="MyAuctionController">here.</a>
		    	<% }
		    	} else {
		    		response.sendRedirect("admin_portal.jsp");
		    		return;
		    	}
			} catch (Exception e) {
			    e.printStackTrace();
			} finally {
				try { rs.close(); } catch (Exception e) {}
				try { ps.close(); } catch (Exception e) {}
		        try { conn.close(); } catch (Exception e) {}
			}
	    %>	
    	</div>
  


</body>
</html>