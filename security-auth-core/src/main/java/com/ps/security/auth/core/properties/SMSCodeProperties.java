package com.ps.security.auth.core.properties;

public class SMSCodeProperties {
    private int codeLength = 6;
    private int expireSecondsIn = 60;
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
