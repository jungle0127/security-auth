package com.ps.security.auth.core.properties;

public class ImageCodeProperties {
	private int width = 67;
	private int height = 23;
	private int codeLength = 4;
	private int expireSecondsIn = 60;
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getCodeLength() {
		return codeLength;
	}
	public void setCodeLength(int codeLength) {
		this.codeLength = codeLength;
	}
	public int getExpireSecondsIn() {
		return expireSecondsIn;
	}
	public void setExpireSecondsIn(int expireSecondsIn) {
		this.expireSecondsIn = expireSecondsIn;
	}
}
