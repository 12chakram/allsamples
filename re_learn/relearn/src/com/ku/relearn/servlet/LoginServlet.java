package com.ku.relearn.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.st.jdbc.utils.GettingOracleConnection;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	String user = request.getParameter("user");
	String pass = request.getParameter("pass");
	
//	if(UserName.equals("kumar") && Password.equals("vayyala")){
//		RequestDispatcher rd = getServletContext().getRequestDispatcher("/sucess.html");
//		rd.forward(request, response);
//	}else{
//		System.out.println("not valid user");
//	}
//	
	
	try {
		
	
		
		Connection con = GettingOracleConnection.getConnection();
	
		System.out.println("connected");
		
		String sql = " SELECT *FROM USERS  WHERE UNAME ='"+user+"' and pass ='"+pass+"'"; 
		
		Statement st = con.createStatement();
		
		System.out.println(sql);
		
		 ResultSet rs = st.executeQuery(sql);
				
		if(rs.next()){
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/sucess.html");
			rd.forward(request, response);
		}
		else{
			System.out.println("not valid user");	
				
		}	
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	}

}
