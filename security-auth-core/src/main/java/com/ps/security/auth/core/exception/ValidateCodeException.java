package com.ps.security.auth.core.exception;

import org.springframework.security.core.AuthenticationException;

public class ValidateCodeException extends AuthenticationException {

	private static final long serialVersionUID = -4273509771484925347L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
