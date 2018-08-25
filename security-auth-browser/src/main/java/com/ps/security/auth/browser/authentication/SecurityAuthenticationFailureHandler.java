package com.ps.security.auth.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.security.auth.browser.dto.SimpleResponse;
import com.ps.security.auth.core.properties.LoginType;
import com.ps.security.auth.core.properties.SecurityProperties;

@Component("securityAuthenticationFailureHandler")
//public class SecurityAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class SecurityAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	private static final Logger logger = LoggerFactory.getLogger(SecurityAuthenticationFailureHandler.class);
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SecurityProperties securityProperties;
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.warn("Login failed.");
		if(LoginType.JSON.equals(this.securityProperties.getBrowserProperties().getLoginType())) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(objectMapper.writeValueAsString(new SimpleResponse(exception.getMessage())));
			response.setStatus(HttpStatus.BAD_REQUEST.value());
		}else {
			super.onAuthenticationFailure(request, response, exception);
		}
	}

}
