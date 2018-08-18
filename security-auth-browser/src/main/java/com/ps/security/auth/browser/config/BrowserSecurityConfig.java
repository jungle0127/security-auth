package com.ps.security.auth.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.ps.security.auth.core.properties.SecurityProperties;
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private AuthenticationSuccessHandler authenticationSucceedHandler;
	@Autowired
	private AuthenticationFailureHandler authenticationFailedHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//UsernamePasswordAuthenticationFilter is for processing the login URL
		http.formLogin()
		//.loginPage("/sign-in.html")
		.loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/form")
		.successHandler(authenticationSucceedHandler)
		.failureHandler(authenticationFailedHandler)
		.and()
		.authorizeRequests()
		.antMatchers("/authentication/require",
				securityProperties.getBrowserProperties().getLoginPage()).permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.csrf().disable();
	}
}
