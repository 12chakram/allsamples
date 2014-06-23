package com.vaannila.service;

import com.vaannila.domain.User;

public class UserServiceImpl implements UserService {


	public void add(User user) {
		//Persist the user object here. 
		System.out.println("Control in User Service Implementation class");
		System.out.println("User added successfully");

	}

}
