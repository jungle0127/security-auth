package com.ps.security.auth.browser.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserAuthenticationService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationService.class);
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info(String.format("%s logged in", username));
		return new User(username, passwordEncoder.encode("pwd"), true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
		// The password should be read from Database.
	}

}
