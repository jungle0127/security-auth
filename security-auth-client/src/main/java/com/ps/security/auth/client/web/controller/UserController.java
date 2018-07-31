package com.ps.security.auth.client.web.controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.ps.security.auth.client.dto.User;
import com.ps.security.auth.client.dto.User.UserDetailView;
import com.ps.security.auth.client.dto.User.UserSimpleView;
import com.ps.security.auth.client.exception.UserNotExistException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(value="user realted interface")
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
	@ApiOperation(value="query users")
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
	public User getUserInfo(
			@ApiParam(value="user id")
			@PathVariable String id) {
		if(StringUtils.equals(id, "666")) {
			throw new UserNotExistException(id);
		}
		User user = new User();
		user.setUserName("ps"); 
		user.setPassword("lotus");
		user.setBirthday(new Date());
		return user;
	}
	@PostMapping("/user")
	public User createUser(@Valid @RequestBody User pojo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		System.out.println("===========================");
		logger.info(ReflectionToStringBuilder.toString(pojo,ToStringStyle.MULTI_LINE_STYLE));
		pojo.setId("1");
		return pojo;
	}
	@PutMapping("/user/{id:\\d+}")
	public User updateUser(@Valid @RequestBody User pojo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			bindingResult.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}
		return pojo;
	}
	@DeleteMapping("/user/{id:\\d+}")
	public void deleteUser(@PathVariable String id){
		logger.info("Delete user by id demo code.");
		return;
	}
}
