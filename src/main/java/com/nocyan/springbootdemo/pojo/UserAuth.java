package com.nocyan.springbootdemo.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
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

}
