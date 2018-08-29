package com.ps.security.auth.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ps.security.auth.browser.authentication.SecurityAuthenticationFailureHandler;
import com.ps.security.auth.browser.authentication.SecurityAuthenticationSuccessHandler;
import com.ps.security.auth.core.authorization.AuthorizeConfigManager;
import com.ps.security.auth.core.filter.ValidateCodeFilter;
import com.ps.security.auth.core.properties.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityAuthenticationSuccessHandler securityAuthenticationSuccessHandler;
	@Autowired
	private SecurityAuthenticationFailureHandler securityAuthenticationFailureHandler;
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private AuthorizeConfigManager authorizeConfigManager;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(securityAuthenticationFailureHandler);

		http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class).formLogin()
				.loginPage("/authentication/require").loginProcessingUrl("/authentication/form")
				.successHandler(securityAuthenticationSuccessHandler)
				.failureHandler(securityAuthenticationFailureHandler)
				// .antMatchers("/*").permitAll()
				.and().csrf().disable();
		this.authorizeConfigManager.config(http.authorizeRequests());
	}
}
