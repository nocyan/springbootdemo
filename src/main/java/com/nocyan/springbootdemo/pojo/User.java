package com.nocyan.springbootdemo.pojo;

public class User {
    private Long id;
    private String nickname;
    private String headerImg;
    private String bio;

    public User() {
    }

    public User(String nickname, String headerImg, String bio) {
        this.nickname = nickname;
        this.headerImg = headerImg;
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", headerImg='" + headerImg + '\'' +
                ", bio='" + bio + '\'' +
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
}
