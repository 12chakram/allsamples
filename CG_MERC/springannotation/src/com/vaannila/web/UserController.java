package com.vaannila.web;

import org.apache.log4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.vaannila.domain.User;
import com.vaannila.service.CountryService;
import com.vaannila.service.CountryServiceList;
import com.vaannila.service.UserService;
import com.vaannila.validator.UserValidator;

import java.util.*;

@Controller
@RequestMapping("/userRegistration.htm")
@SessionAttributes("user")
public class UserController {
	


	private UserService userService;
	private CountryServiceList countryServiceList;
	private UserValidator userValidator;

//UserService userService=new UserServiceIMPL();
	@Autowired
	public void setUserService(UserService userService) 
	{
		System.out.println("control in setUserService Method----->");
		this.userService = userService;
	}
	
	@Autowired
	public void setCountryServiceList(CountryServiceList countryServiceList)
	{
		this.countryServiceList=countryServiceList;
	}
	
	
	
	@Autowired
	public void setUserValidator(UserValidator userValidator) {
		this.userValidator = userValidator;
	}
	

	@ModelAttribute("communityList")
	public List<String> communityList()
	{
		
		List<String> communityList=new ArrayList();
		communityList.add("Hibernate");
		communityList.add("Struts");
		communityList.add("Spring");
		communityList.add("JSTL");
		return communityList;
	}
	
	@ModelAttribute("countryList")
	public List<CountryService> countryList()
	{
		List<CountryService> countryList=new ArrayList();
		
		countryList=countryServiceList.getCountry();
		
		return countryList;
		
		
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm(ModelMap model)
	{
		System.out.println("control in showUserForm Method----->");
		User user = new User();
		model.addAttribute(user);
		return "userForm";
		//the controll goes to directly userform.jsp page
	}

	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("user") User user,BindingResult result) 
	{
		userValidator.validate(user, result);
		if (result.hasErrors()) {
			return "userForm";
			} else {
		System.out.println("control in onSubmit Method----->");
		userService.add(user);
		return "redirect:userSuccess.htm";
				//it will search for suitable request mapping in controller only
			}
	}
	
}
