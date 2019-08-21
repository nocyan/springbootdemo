package com.nocyan.springbootdemo.pojo;

public class GithubUser {
    private String login;
    private String name;
    private long id;
    private String email;
    private String bio;

    public GithubUser() {
    }

    public GithubUser(String login, String name, long id, String email, String bio) {
        this.login = login;
        this.name = name;
        this.id = id;
        this.email = email;
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
