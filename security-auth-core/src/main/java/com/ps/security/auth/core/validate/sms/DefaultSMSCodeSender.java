package com.ps.security.auth.core.validate.sms;

import org.springframework.stereotype.Component;

public class DefaultSMSCodeSender implements ISMSCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println(String.format("Send validate sms code: %s to phone:%s", code,mobile));
    }
}
