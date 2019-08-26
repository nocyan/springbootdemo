package com.nocyan.springbootdemo.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private Long id;
    private String nickname;
    private String headerImg;
    private String bio;
    private Long createTime;
    private Timestamp updateTime;

    public User() {
    }

    public User(String nickname, String headerImg, String bio,Long createTime) {
        this.nickname = nickname;
        this.headerImg = headerImg;
        this.bio = bio;
        this.createTime=createTime;
    }

}
