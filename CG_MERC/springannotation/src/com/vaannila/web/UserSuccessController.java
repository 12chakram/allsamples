package com.vaannila.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserSuccessController {

	@RequestMapping("/userSuccess.htm")
	public String redirect1()
	{
		System.out.println("control in redirect method in user successController class");
		return "userSuccess";
	}
}
