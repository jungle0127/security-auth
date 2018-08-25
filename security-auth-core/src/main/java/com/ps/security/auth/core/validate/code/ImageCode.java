package com.ps.security.auth.core.validate.code;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

public class ImageCode {
	private String code;
	private LocalDateTime expireTime;
	private BufferedImage image;
	public ImageCode(String code, BufferedImage image, LocalDateTime expireTime) {
		this.code = code;
		this.image = image;
		this.expireTime = expireTime;
	}
	public ImageCode(String code, BufferedImage image, int seconds) {
		this.code = code;
		this.image = image;
		this.expireTime = LocalDateTime.now().plusSeconds(seconds);
	}
	
	public boolean isExpired() {
		return LocalDateTime.now().isAfter(expireTime);
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public LocalDateTime getExpireTime() {
		return expireTime;
	}
	public void setExpireTime(LocalDateTime expireTime) {
		this.expireTime = expireTime;
	}
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
	
}
