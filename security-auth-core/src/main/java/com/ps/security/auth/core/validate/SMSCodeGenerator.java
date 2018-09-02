package com.ps.security.auth.core.validate;

import com.ps.security.auth.core.properties.SecurityProperties;
import com.ps.security.auth.core.validate.code.ValidateCode;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

@Component("smsCodeGenerator")
public class SMSCodeGenerator implements IValidateCodeGenerator {
    @Autowired
    private SecurityProperties securityProperties;

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }
    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }

    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.random(securityProperties.getValidateCode().getSmsCode().getCodeLength());
        return new ValidateCode(code,securityProperties.getValidateCode().getSmsCode().getExpireSecondsIn());
    }
}
