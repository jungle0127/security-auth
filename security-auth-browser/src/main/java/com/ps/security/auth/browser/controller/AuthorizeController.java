package com.ps.security.auth.browser.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.ps.security.auth.browser.dto.SimpleResponse;
import com.ps.security.auth.core.properties.SecurityProperties;

@RestController
@RequestMapping("/authentication")
public class AuthorizeController {
	private static final Logger logger = LoggerFactory.getLogger(AuthorizeController.class);
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	// request url
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@GetMapping("/require")
	@ResponseStatus(code=HttpStatus.UNAUTHORIZED)
	public SimpleResponse requireAuthentication(HttpServletRequest request,HttpServletResponse response) throws IOException {
		SavedRequest savedRequest = this.requestCache.getRequest(request, response);
		if(savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			if(StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				this.redirectStrategy.sendRedirect(request, response, this.securityProperties.getBrowserProperties().getLoginPage());
			}			
		}
		return new SimpleResponse("The service needs authentication, please redirect to login page.");
	}
}
