package com.ps.security.auth.browser.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ps.security.auth.core.properties.SecurityProperties;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.formLogin()
		.loginPage("/authentication/require")
		.loginProcessingUrl("/authentication/form")
		.and()
		.authorizeRequests()
		.antMatchers("/sign-in.html","/authentication/require","authentication/form",this.securityProperties.getBrowserProperties().getLoginPage()).permitAll()
//		.antMatchers("/*").permitAll()
		.anyRequest()
		.authenticated()
		.and()
		.csrf().disable();
	}
}
