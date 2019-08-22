package com.nocyan.springbootdemo.service.impl;

import com.nocyan.springbootdemo.mapper.UserMapper;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(long id) {
        return userMapper.selectUser(id);
    }

    @Override
    public Long registerUser(User user) {
        userMapper.insertUser(user);
        return user.getId();
    }

    @Override
    public Long registerUserAuth(UserAuth userAuth) {
        userMapper.insertUserAuth(userAuth);
        return userAuth.getId();
    }

    @Override
    public UserAuth getUserAuth(String identifier) {
        return userMapper.selectUserAuth(identifier);
    }
}
