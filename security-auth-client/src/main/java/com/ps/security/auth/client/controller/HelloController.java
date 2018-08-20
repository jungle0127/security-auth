package com.ps.security.auth.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ps.security.auth.core.properties.SecurityProperties;

@RestController
public class HelloController {
	@Autowired
	private SecurityProperties secruityProperites;
	@GetMapping("/hello")
	public String hello() {
		return this.secruityProperites.getBrowserProperties().getLoginPage();
	}
}
