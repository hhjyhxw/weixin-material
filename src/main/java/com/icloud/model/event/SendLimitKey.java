package com.icloud.model.event;

public class SendLimitKey {
    private String sendType="zlld";//现在的发送类型（默认 "zlld"） 从真龙乐豆页面跳转

    private String openid;

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType == null ? null : sendType.trim();
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }
}