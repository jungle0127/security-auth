package com.ps.security.auth.testrunner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.ps.security.auth.core.properties.SecurityProperties;
@Component
public class PropertiesConfigRunner implements CommandLineRunner {
	@Autowired
	private SecurityProperties config;
	
	@Override
	public void run(String... arg0) throws Exception {
		System.out.println(config.getBrowser().getLoginPage());
		System.out.println(config.getBrowser().getLoginType());
	}

}
