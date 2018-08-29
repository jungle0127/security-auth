package com.ps.security.auth.core.authorization;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.stereotype.Component;

@Component
public class CommonSecurityAuthorizeConfigManager implements AuthorizeConfigManager {
	@Autowired
	private Set<AuthorizeConfigProvider> authorizeConfigProviders;
	
	@Override
	public void config(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config) {
		for(AuthorizeConfigProvider provider: authorizeConfigProviders) {
			provider.config(config);
		}
		config.anyRequest().authenticated();
	}

}
