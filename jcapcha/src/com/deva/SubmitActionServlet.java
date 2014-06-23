package com.deva;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.module.servlet.SimpleSoundCaptchaServlet;

/**
 * Servlet implementation class SubmitActionServlet
 */

public class SubmitActionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitActionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userCaptchaResponse = request.getParameter("jcaptcha");
			      boolean captchaPassed = SimpleSoundCaptchaServlet.validateResponse(request, userCaptchaResponse);
			      if(captchaPassed){
			            response.sendRedirect("jcaptcha.jsp?message=captcha%20passed");
			        }else{
		           response.sendRedirect("jcaptcha.jsp?message=captcha%20failed");
			        }
			      
	}

}
