package com.jsp.servletChaining;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

import com.mysql.jdbc.PreparedStatement;
@WebServlet("/VerificationOfpasword")
public class VerificationOfpasword  extends GenericServlet
{

	
	@Override
	public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException 
	{
		//fetch password
		String password =request.getParameter("Password");
		String url = "jdbc:mysql://localhost:3306?user=root&password=0909";
		String query = "select * from teja18.mydetails where password =?";
		
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(url);
			PreparedStatement ps = (PreparedStatement) connection.prepareStatement(query);
			ps.setString(1, password);
			ResultSet rs = ps.executeQuery();
			PrintWriter writer = response.getWriter();
			if (rs.next()) 
			{
				writer.println("<h1 style ='color:green';>LOGIN SUCCESSFULL</h1>");
				writer.println("<h1 style ='color:blue';>Welcome"+rs.getString("FullName")+"</h1>");
				RequestDispatcher dispatcher = request.getRequestDispatcher("Gmail.html");
				dispatcher.include(request, response);
			}else
			{
				writer.println("<h1 style ='color:red';>PLEASE ENTER VALID PASSWORD</h1>");
				RequestDispatcher dispatcher= request.getRequestDispatcher("verifyEmail.html");
				dispatcher.include(request, response);
			}
			connection.close();
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
