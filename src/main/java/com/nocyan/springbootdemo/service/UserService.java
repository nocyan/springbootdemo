package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.exception.UserException;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.provider.OAuthProvider;

public interface UserService {
    User getUser(long id);
    Long registerUser(User user);
    Long registerUserAuth(UserAuth userAuth) throws UserException;
    UserAuth checkUserAuth(String identifier,Integer authType);
    boolean checkOAuthAccessToken(String accessToken,Class<? extends OAuthProvider> providerClazz);
    UserAuth checkAndInitOAuthUserAuth(Integer authType, String identifier) throws UserException;
    long verifyUserAuth(UserAuth userAuth);
}