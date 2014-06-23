package com.ku.mylearn.learn.servlet3;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.xml.internal.ws.developer.UsesJAXBContext;

/**
 * Servlet implementation class WelcomServlet
 */
@WebServlet( name="/WelcomServlet" ,urlPatterns={"/WelcomeServlet"} )
public class WelcomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
			}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	  // get the values from the parameter 
		
		String name = request.getParameter("name");
		String welcomemessage = "welcome"+name;
		
		// set the content type (MIME type ) of the response
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		
		// write the html to response
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title> A very simple servlet example</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<h1>"+welcomemessage+"</h1>");
		out.println("</body>");
		out.println("</html>");
		out.close();
		 
	
	}

}
