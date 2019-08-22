package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;

public interface UserService {
    User getUser(long id);
    Long registerUser(User user);
    Long registerUserAuth(UserAuth userAuth);
    UserAuth getUserAuth(String identifier);
}
