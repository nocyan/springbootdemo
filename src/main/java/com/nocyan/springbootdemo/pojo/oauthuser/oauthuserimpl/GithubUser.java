package com.nocyan.springbootdemo.pojo.oauthuser.oauthuserimpl;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.oauthuser.OAuthUser;

public class GithubUser implements OAuthUser {
    private String login;
    private String name;
    private long githubUid;
    private String email;
    private String bio;
    private long myuid;

    public GithubUser() {
    }

    public GithubUser(String login, String name, long githubUid, String email, String bio, long myuid) {
        this.login = login;
        this.name = name;
        this.githubUid = githubUid;
        this.email = email;
        this.bio = bio;
        this.myuid = myuid;
    }

    @Override
    public long getOAuthId() {
        return githubUid;
    }

    @Override
    public String getOAuthName() {
        if (name != null && !name.equals(""))
            return name;
        else return login;
    }

    @Override
    public long getMyUserid() {
        return myuid;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", githubUid=" + githubUid +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", myuid=" + myuid +
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

    public long getGithubUid() {
        return githubUid;
    }


    public void setGithubUid(long githubUid) {
        this.githubUid = githubUid;
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

    public long getMyuid() {
        return myuid;
    }

    public void setMyuid(long myuid) {
        this.myuid = myuid;
    }
}
