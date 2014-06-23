package com.vaannila.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.FileAppender;
import org.apache.log4j.*;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.vaannila.domain.User;
import com.vaannila.service.UserService;

public class UserController extends SimpleFormController {

	private UserService userService;
	protected final Log logger = LogFactory.getLog(getClass());
	//static Logger logger = Logger.getLogger("PatternLayoutExample");
	public UserController()	{
		System.out.println("Control in UserController Constructor--->");
		setCommandClass(User.class);//form bean 
		setCommandName("user");
	}
	
	public void setUserService(UserService userService) {
		System.out.println("Control in UserService method--->");
		this.userService = userService;
	}
    
	
	protected ModelAndView onSubmit(Object command) throws Exception//Object will have the state of form bean
	{
System.out.println("Control in onsubmit method--->");
		 FileAppender fileappender = new FileAppender(new PatternLayout(),"output.txt");
	//	 logger.addAppender(fileappender);
		//logger.addAppender(fileappender);
		 
		logger.info("control in onSubmit method");
		User user = (User) command;
		userService.add(user);
		return new ModelAndView("userSuccess","user",user);
		//control goes to userSuccess.jsp
		//user object will be added to user String
		

	}
	
}
