package com.nocyan.springbootdemo.DTO.OAuthDTOimpl;

import com.nocyan.springbootdemo.DTO.OAuthDTO;
import com.nocyan.springbootdemo.enums.AuthTypeEnum;
import lombok.Data;

@Data
public class GithubUserDTO implements OAuthDTO {
    private String login;
    private String name;
    private long githubUid;
    private String email;
    private String bio;
    private String avatar_url;

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

}
