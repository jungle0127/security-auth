package com.ps.security.auth.core.validate.sms;

public interface ISMSCodeSender {
    void send(String mobile, String code);
}
