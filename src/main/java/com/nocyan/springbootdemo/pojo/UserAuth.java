package com.nocyan.springbootdemo.pojo;

import java.sql.Timestamp;

public class UserAuth {
    private Long id;
    private Long uid;
    private Integer authType;
    private String identifier;
    private String credential;
    private Long createTime;
    private Timestamp updateTime;

    public UserAuth() {
    }

    public UserAuth(Long uid, Integer authType, String identifier, String credential,Long createTime) {
        this.uid = uid;
        this.authType = authType;
        this.identifier = identifier;
        this.credential = credential;
        this.createTime=createTime;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "id=" + id +
                ", uid=" + uid +
                ", authType=" + authType +
                ", identifier='" + identifier + '\'' +
                ", credential='" + credential + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Integer getAuthType() {
        return authType;
    }

    public void setAuthType(Integer authType) {
        this.authType = authType;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public String getCredential() {
        return credential;
    }

    public void setCredential(String credential) {
        this.credential = credential;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
