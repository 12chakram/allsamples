//Action.java
package com.st.struts.actions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class ValidateAction extends Action{
	
	//over ride execute method
	
	public ActionForward execute
	               (ActionMapping am,ActionForm af,
 HttpServletRequest req, HttpServletResponse res)throws Exception{
									
		
		// Retrieve the client sent parameters 
		
		
		String name= req.getParameter("uname");
		
		
		String pass = req.getParameter("pass");
		
		
		/*here we dont implement the logic  to connect db, 
		 * as struts is limited to presentation tier 
		 * 
		 * ( we are highly recommended to  dothis
		 * 
		 */
		 
		// recall control flow statement 
		
		if(name.equals("kumar")&& pass.equals("sttech")){
		
			
			return am.findForward("sucess");
	
			
		}//if
		
		return am.findForward("fail");
				
				
	}//execute
	

}// class
