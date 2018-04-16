package com.icloud.model.event;

import java.util.Date;

/**
 * 限制发送表
 * @author z
 *
 */
public class SendLimit extends SendLimitKey {
    private Date createTime;

    private String status;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}