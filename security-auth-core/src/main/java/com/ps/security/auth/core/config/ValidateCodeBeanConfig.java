package com.ps.security.auth.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ps.security.auth.core.properties.SecurityProperties;
import com.ps.security.auth.core.validate.DefaultImageCodeGenerator;
import com.ps.security.auth.core.validate.IImageCodeGenerator;

@Configuration
public class ValidateCodeBeanConfig {
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name="imageCodeGenerator")
	public IImageCodeGenerator getImageCodeGenerator() {
		DefaultImageCodeGenerator imageCodeGenerator = new DefaultImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
}
