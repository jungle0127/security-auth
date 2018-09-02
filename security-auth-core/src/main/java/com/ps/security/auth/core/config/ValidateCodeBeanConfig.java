package com.ps.security.auth.core.config;

import com.ps.security.auth.core.validate.sms.DefaultSMSCodeSender;
import com.ps.security.auth.core.validate.sms.ISMSCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ps.security.auth.core.properties.SecurityProperties;
import com.ps.security.auth.core.validate.ImageCodeGenerator;
import com.ps.security.auth.core.validate.IValidateCodeGenerator;

@Configuration
public class ValidateCodeBeanConfig {
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	@ConditionalOnMissingBean(name="imageCodeGenerator")
	public IValidateCodeGenerator getImageCodeGenerator() {
		ImageCodeGenerator imageCodeGenerator = new ImageCodeGenerator();
		imageCodeGenerator.setSecurityProperties(securityProperties);
		return imageCodeGenerator;
	}
	@Bean
	@ConditionalOnMissingBean(ISMSCodeSender.class)
	public ISMSCodeSender getSMSCodeSender(){
		return new DefaultSMSCodeSender();
	}
}
