package com.nocyan.springbootdemo.DTO.OAuthDTOimpl;

import com.nocyan.springbootdemo.DTO.OAuthDTO;
import com.nocyan.springbootdemo.enums.AuthTypeEnum;

public class GithubUserDTO implements OAuthDTO {
    private String login;
    private String name;
    private long githubUid;
    private String email;
    private String bio;

    public GithubUserDTO() {
    }

    public GithubUserDTO(String login, String name, long githubUid, String email, String bio) {
        this.login = login;
        this.name = name;
        this.githubUid = githubUid;
        this.email = email;
        this.bio = bio;
    }

    @Override
    public String getIdentifier() {
        return login;
    }

    @Override
    public String getCredential() {
        return null;
    }

    @Override
    public AuthTypeEnum getAuthType() {
        return AuthTypeEnum.GITHUB;
    }


    @Override
    public String toString() {
        return "GithubUserDTO{" +
                "login='" + login + '\'' +
                ", name='" + name + '\'' +
                ", githubUid=" + githubUid +
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

}
