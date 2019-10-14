package com.cs336.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import com.cs336.javafiles.Mail;





@WebServlet("/messageController")
public class messageController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Resource(name="jdbc/cs336db")
	private DataSource dataSource;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    HttpSession session=request.getSession(false);
		String user_name = (session.getAttribute("user")).toString();
		
		List<Mail> mail_list= getMail(user_name);
		request.setAttribute("mail_ll", mail_list);
		
		RequestDispatcher rs = request.getRequestDispatcher("inbox.jsp");
        rs.forward(request, response);
		
	}

	private List<Mail> getMail(String user_name) {
	
		List<Mail> mail = new LinkedList<Mail>();
		Connection myConn=null;
		PreparedStatement myStmt=null;
		ResultSet rs = null;
		
			try {
				myConn=dataSource.getConnection();
				String sql="select * from email where mail_to=? order by mail_id desc";
				myStmt=myConn.prepareStatement(sql);
				myStmt.setString(1, user_name);
				rs=myStmt.executeQuery();
				
				while(rs.next()) {
					String mt= rs.getString("mail_to");
					String mf=rs.getString("mail_from");
					String msg=rs.getString("message");
					Mail temp= new Mail(mt,mf,msg);
					mail.add(temp);
				}
				
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				close (myConn,myStmt,rs);
			}
		return mail;
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try {
			if(myRs!=null) {
				myRs.close();
			}
			if(myStmt!=null) {
				myStmt.close(); 
			}
			if(myConn!=null) {
				myConn.close();
			}
		}
		catch(Exception exc) {
			exc.printStackTrace();
		}
	}

}
