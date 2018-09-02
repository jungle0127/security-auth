package com.ps.security.auth.core.controller;

import java.io.IOException;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ps.security.auth.core.validate.code.ValidateCode;
import com.ps.security.auth.core.validate.sms.ISMSCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import com.ps.security.auth.core.validate.IValidateCodeGenerator;
import com.ps.security.auth.core.validate.code.ImageCode;

@RestController
public class ValidateCodeController {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    @Autowired
    private IValidateCodeGenerator imageCodeGenerator;
    @Autowired
    private IValidateCodeGenerator smsCodeGenerator;
    @Autowired
    private ISMSCodeSender smsCodeSender;

    @GetMapping("/code/image")
    public void createImageCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ImageCode imageCode = (ImageCode) imageCodeGenerator.generate(new ServletWebRequest(request));
        this.sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        ImageIO.write(imageCode.getImage(), "PNG", response.getOutputStream());
    }

    @GetMapping("/code/sms")
    public void createSmsCode(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletRequestBindingException {
        ValidateCode smsCode = smsCodeGenerator.generate(new ServletWebRequest(request));
        this.sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, smsCode);
        String phoneNumber = ServletRequestUtils.getRequiredStringParameter(request,"phoneNumber");
        smsCodeSender.send(phoneNumber,smsCode.getCode());
    }
}
