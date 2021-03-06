package com.ps.security.auth.client.dto;

import java.util.Date;

import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonView;
import com.ps.security.auth.client.validator.MyConstraint;

import io.swagger.annotations.ApiModelProperty;

public class User {
	
	public interface UserSimpleView {};
	public interface UserDetailView extends UserSimpleView {};
	
	private String id;
	
	@ApiModelProperty(value="user name")
	private String userName;
	
	@NotBlank(message="Password can not be null.")
	private String password;
	
	@Past(message="Birthday must be past date.")
	@MyConstraint(message="This is a demo constraint, ignore it.")
	@ApiModelProperty(value="age of user.")
	private Date birthday;
	@JsonView(UserSimpleView.class)
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonView(UserSimpleView.class)
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@JsonView(UserDetailView.class)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@JsonView(UserSimpleView.class)
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}
