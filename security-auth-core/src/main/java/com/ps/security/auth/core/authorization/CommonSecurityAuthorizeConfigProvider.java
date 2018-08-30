package com.ps.security.auth.core.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

import com.ps.security.auth.core.properties.SecurityProperties;

@Component
@Order(Integer.MIN_VALUE)
public class CommonSecurityAuthorizeConfigProvider implements AuthorizeConfigProvider {
	@Autowired
	private SecurityProperties securityProperties;
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		config.antMatchers("/sign-in.html", 
				"/code/image", 
				"/authentication/require", 
				"authentication/form",
				this.securityProperties.getBrowser().getLoginPage())
		.permitAll();
		
		

	}

}
