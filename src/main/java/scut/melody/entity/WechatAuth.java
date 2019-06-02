package scut.melody.entity;

import java.util.Date;

public class WechatAuth {
    private Integer wechatAuthId;

    private String userId;

    private String openid;

    private Date createTime;

    private Date updateTime;

    public Integer getWechatAuthId() {
        return wechatAuthId;
    }

    public void setWechatAuthId(Integer wechatAuthId) {
        this.wechatAuthId = wechatAuthId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}