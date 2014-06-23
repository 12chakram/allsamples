package com.st.servlets.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.st.jdbc.daoI.UserDAOI;
import com.st.jdbc.vo.User;


public class UserLoginServlet  extends HttpServlet{
	
	private UserDAOI udao;

	public void service(HttpServletRequest req, HttpServletResponse res)
	throws ServletException,IOException{
		
	// get the uname and pass
		
		String uname =req.getParameter("uname");
		String pass = req.getParameter("pass");
		
		User u = udao.findUser(uname,pass);

		if(u!=null){  // userlogindetails are valied
			
			HttpSession hs = req.getSession();
			
			// add user object to session
			
			hs.setAttribute("user", u);
			
			//forward req to homeservlet
			
			req.getRequestDispatcher("/home").forward(req, res);
			
			
		}//if
		else{
			
			res.setContentType("text/html");
			
			res.getWriter().println("<i> userlogin details are not valid<i><br>Try Again<br/>");
			req.getRequestDispatcher("/Userlogin.html").include(req,res);
			
			
			}//else
	}
	 public void init(){
		 
		 udao = DAOFactory.getUserDAO();
	 }
		
		
		
		
	}//service
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
