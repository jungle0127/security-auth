package com.ps.security.auth.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ps.security.auth.core.properties.LoginType;
import com.ps.security.auth.core.properties.SecurityProperties;

@Component("securityAuthenticationSuccessHandler")
//public class SecurityAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
public class SecurityAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private static final Logger logger = LoggerFactory.getLogger(SecurityAuthenticationSuccessHandler.class);
	
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("Login succeed");
		if(LoginType.JSON.equals(this.securityProperties.getBrowserProperties().getLoginType())) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(this.objectMapper.writeValueAsString(authentication));
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
