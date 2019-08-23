package com.nocyan.springbootdemo.provider;

public interface OAuthProvider {
    boolean checkAccessToken(String accessToken);
}
