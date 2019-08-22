package com.nocyan.springbootdemo.pojo;

public class UserAuth {
    private Long id;
    private Long uid;
    private Integer authType;
    private String identifier;
    private String credential;

    public UserAuth() {
    }

    public UserAuth(Long uid, Integer authType, String identifier, String credential) {
        this.uid = uid;
        this.authType = authType;
        this.identifier = identifier;
        this.credential = credential;
    }

    @Override
    public String toString() {
        return "UserAuth{" +
                "id=" + id +
                ", uid=" + uid +
                ", authType=" + authType +
                ", identifier='" + identifier + '\'' +
                ", credential='" + credential + '\'' +
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
}
