package com.ps.security.auth.client.web.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ps.security.auth.client.dto.User;

@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/user")
	public List<User> queryUser(@RequestParam(required=true,defaultValue="Jerry") String userName) {
		List<User> userList = new LinkedList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
	@GetMapping("/usr")
	public List<User> queryUser(User userPojo){
		System.out.println(ReflectionToStringBuilder.toString(userPojo,ToStringStyle.MULTI_LINE_STYLE));
		List<User> userList = new LinkedList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
}
