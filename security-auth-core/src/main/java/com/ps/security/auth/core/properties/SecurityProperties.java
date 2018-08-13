package com.ps.security.auth.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="ps.security")
public class SecurityProperties {
	private BrowserProperties browserProperties = new BrowserProperties();

	public BrowserProperties getBrowserProperties() {
		return browserProperties;
	}

	public void setBrowserProperties(BrowserProperties browserProperties) {
		this.browserProperties = browserProperties;
	}
	
}
