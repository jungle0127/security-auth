package com.ps.security.auth.core.validate;

import javax.servlet.http.HttpServletRequest;

import com.ps.security.auth.core.validate.code.ImageCode;

public interface IImageCodeGenerator {
	ImageCode createImageCode(HttpServletRequest request);
}
