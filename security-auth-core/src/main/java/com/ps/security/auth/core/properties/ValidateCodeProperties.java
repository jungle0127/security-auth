package com.ps.security.auth.core.properties;

public class ValidateCodeProperties {
    private ImageCodeProperties imageCode = new ImageCodeProperties();
    private SMSCodeProperties smsCode = new SMSCodeProperties();

    public SMSCodeProperties getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(SMSCodeProperties smsCode) {
        this.smsCode = smsCode;
    }

    public ImageCodeProperties getImageCode() {
        return imageCode;
    }

    public void setImageCode(ImageCodeProperties imageCode) {
        this.imageCode = imageCode;
    }

}
