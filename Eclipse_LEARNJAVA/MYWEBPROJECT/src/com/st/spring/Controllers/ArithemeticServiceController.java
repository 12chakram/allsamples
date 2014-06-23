//ArithemeticServiceController.java 

package com.st.spring.Controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

public class ArithemeticServiceController extends MultiActionController {
	
	public ModelAndView add (HttpServletRequest req, HttpServletRequest res, Object command , BindException e  ){
		
		int op1= Integer.parseInt( req.getParameter("op1"));
		
		int op2= Integer.parseInt( req.getParameter("op2"));
		
		int result = op1+op2;
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/Home.jsp");
		mav.addObject("result", result);
		
		return mav;
		
	}//add 
	
	
public ModelAndView subtract  (HttpServletRequest req, HttpServletRequest res, Object command , BindException e  ){
		
		int op1= Integer.parseInt( req.getParameter("op1"));
		
		int op2= Integer.parseInt( req.getParameter("op2"));
		
		int result = op1-op2;
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("/Home.jsp");
		mav.addObject("result", result);
		
		return mav;
		
	
}//subtract 
	
	

}// class 
