package com.ps.security.auth.core.properties;

public class BrowserProperties {
	private String loginPage = "/sign-in.html";
	
	private LoginType loginType = LoginType.REDIRECT;
	
	private int rememberMeSeconds = 3600;
	
	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public String getLoginPage() {
		return loginPage;
	}

	public void setLoginPage(String loginPage) {
		this.loginPage = loginPage;
	}

	public int getRememberMeSeconds() {
		return rememberMeSeconds;
	}

	public void setRememberMeSeconds(int rememberMeSeconds) {
		this.rememberMeSeconds = rememberMeSeconds;
	}
	
}
