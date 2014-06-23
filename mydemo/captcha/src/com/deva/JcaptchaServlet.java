package com.deva;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.octo.captcha.module.servlet.image.SimpleImageCaptchaServlet;

/**
 * Servlet implementation class JcaptchaServlet
 */
@WebServlet("/submit")
public class JcaptchaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public JcaptchaServlet() {
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
//		System.out.println("=============================================-----------");
//		String userCaptchaResponse = request.getParameter("jcaptcha");
//		boolean captchaPassed = SimpleImageCaptchaServlet.validateResponse(request, userCaptchaResponse);
//		if(captchaPassed){
//			
//		System.out.println("proceed to submit action");
//		}else{
//			System.out.println("error to user"); 
//		}
		String	captchaId = "";
		try{
		captchaId = request.getParameter("captchaId");
		byte bufCharacters[] = Main.getCaptchaCharacters(captchaId, "en", "US");
		
		
		
		response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        ServletOutputStream out = response.getOutputStream();
        out.write(bufCharacters);
        out.flush();
        out.close();
        String chars = Main.getTextCaptcha(captchaId, "en", "US");
        System.out.println(chars);
		}catch(Exception e){
			
		}
		
	}

}
