package com.ps.security.auth.core.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ps.security.auth.core.controller.ValidateCodeController;
import com.ps.security.auth.core.exception.ValidateCodeException;
import com.ps.security.auth.core.validate.code.ImageCode;

public class ValidateCodeFilter extends OncePerRequestFilter {
	private AuthenticationFailureHandler authenticationFailureHandler;
	public AuthenticationFailureHandler getAuthenticationFailureHandler() {
		return authenticationFailureHandler;
	}

	public void setAuthenticationFailureHandler(AuthenticationFailureHandler authenticationFailureHandler) {
		this.authenticationFailureHandler = authenticationFailureHandler;
	}

	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		if(StringUtils.equals("/security/authentication/form", request.getRequestURI()) 
				&& StringUtils.equalsIgnoreCase(request.getMethod(), "POST")) {
			try {
				validateImageCode(new ServletWebRequest(request));
			} catch(ValidateCodeException e) {
				this.authenticationFailureHandler.onAuthenticationFailure(request, response, e);
				return;
			}
		}
		filterChain.doFilter(request, response);
	}

	private void validateImageCode(ServletWebRequest request) throws ServletRequestBindingException {
		ImageCode imageCodeInSession = (ImageCode) this.sessionStrategy.getAttribute(request, ValidateCodeController.SESSION_KEY);
		String imageCodeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");
		if(StringUtils.isBlank(imageCodeInRequest)) {
			throw new ValidateCodeException("Image code can not be null.");
		}
		if(imageCodeInSession == null) {
			throw new ValidateCodeException("Image code does not exist.");
		}
		if(imageCodeInSession.isExpired()) {
			this.sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
			throw new ValidateCodeException("Image code is expired.");
		}
		if(!StringUtils.equals(imageCodeInRequest, imageCodeInSession.getCode())) {
			throw new ValidateCodeException("Image code does not match");
		}
		this.sessionStrategy.removeAttribute(request, ValidateCodeController.SESSION_KEY);
	}


}
