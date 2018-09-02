package com.ps.security.auth.core.validate;


import com.ps.security.auth.core.validate.code.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

public interface IValidateCodeGenerator {
	ValidateCode generate(ServletWebRequest request);
}
