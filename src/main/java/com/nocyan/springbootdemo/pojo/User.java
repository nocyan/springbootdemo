package com.nocyan.springbootdemo.pojo;

import java.sql.Timestamp;

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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", bio='" + bio + '\'' +
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
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
