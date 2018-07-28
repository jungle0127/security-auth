package com.ps.security.auth.client.web.controller;

import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.ps.security.auth.client.dto.User;
import com.ps.security.auth.client.dto.User.UserDetailView;
import com.ps.security.auth.client.dto.User.UserSimpleView;

@RestController
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@GetMapping("/user")
	@JsonView(UserSimpleView.class)
	public List<User> queryUser(@RequestParam(required=true,defaultValue="Jerry") String userName) {
		List<User> userList = new LinkedList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
	@GetMapping("/usr")
	@JsonView(UserSimpleView.class)
	public List<User> queryUser(User userPojo, @PageableDefault(page=1,size=15,sort="username, asc") Pageable pageable){
		logger.info(ReflectionToStringBuilder.toString(userPojo,ToStringStyle.MULTI_LINE_STYLE));
		logger.info(String.format("pageName:%d",pageable.getPageNumber()));
		
		List<User> userList = new LinkedList<>();
		userList.add(new User());
		userList.add(new User());
		userList.add(new User());
		return userList;
	}
	@GetMapping("/user/{id:\\d+}")
	@JsonView(UserDetailView.class)
	public User getUserInfo(@PathVariable String id) {
		User user = new User();
		user.setUserName("ps");
		user.setPassword("lotus");
		return user;
	}
}
