package com.ps.security.auth.core.controller;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import com.ps.security.auth.core.validate.IImageCodeGenerator;
import com.ps.security.auth.core.validate.code.ImageCode;

@RestController
public class ImageCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	@Autowired
	private IImageCodeGenerator imageCodeGenerator;
	
	@GetMapping("/code/image")
	public void createImageCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ImageCode imageCode = imageCodeGenerator.createImageCode(request);
		this.sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
		ImageIO.write(imageCode.getImage(), "PNG", response.getOutputStream());
	}
}
