package com.nocyan.springbootdemo.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.Util.HttpUtil;
import com.nocyan.springbootdemo.exception.UserException;
import com.nocyan.springbootdemo.mapper.UserMapper;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.provider.OAuthProvider;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;
import java.lang.reflect.Field;

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUser(long id) {
        return userMapper.selectUser(id);
    }

    private Long registerUser(User user) {
        userMapper.insertUser(user);
        return user.getId();
    }

    @Override
    @Transactional(propagation = Propagation.NESTED)
    public Long registerUserAuth(UserAuth userAuth) throws UserException {
        if (!checkNotNullUserAuth(userAuth))
            throw new UserException("user auth param error");
        if (checkExistUserAuth(userAuth))
            throw new UserException("user is already existed");
        if (userAuth.getUid() == null) {
            User user = new User();
            user.setNickname(userAuth.getIdentifier());
            user.setCreateTime(System.currentTimeMillis());
            userAuth.setUid(registerUser(user));
        }
        userAuth.setCreateTime(System.currentTimeMillis());
        userMapper.insertUserAuth(userAuth);
        return userAuth.getId();
    }

    @Override
    public UserAuth checkUserAuth(String identifier, Integer authType) {
        return userMapper.checkUserAuth(identifier, authType);
    }

    @Override
    public boolean checkOAuthAccessToken(String accessToken, Class<? extends OAuthProvider> providerClazz) {
        boolean success = false;
        try {
            OAuthProvider oAuthProvider = providerClazz.getDeclaredConstructor().newInstance();
            HttpUtil httpUtil = new HttpUtil();
            Field httpUtilFiled = providerClazz.getDeclaredField("httpUtil");
            httpUtilFiled.setAccessible(true);
            httpUtilFiled.set(oAuthProvider, httpUtil);
            success = (boolean) providerClazz.getMethod("checkAccessToken", String.class).invoke(oAuthProvider, accessToken);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }

    /*
     * 检查userAuth是否进行过注册，若没有则自动注册
     * */
    @Override
    public UserAuth checkAndInitOAuthUserAuth(Integer authType, String identifier) throws UserException {
        UserAuth userAuth = checkUserAuth(identifier, authType);
        if (userAuth == null) {
            userAuth = new UserAuth(null, authType, identifier, null, null);
            //注册账号认证
            registerUserAuth(userAuth);
        }
        return userAuth;
    }

    @Override
    public long verifyUserAuth(UserAuth userAuth) {
        long uid = -1;
        UserAuth userAuth1 = userMapper.selectUserAuth(userAuth.getIdentifier(), userAuth.getAuthType(), userAuth.getCredential());
        if (userAuth1 != null) uid = userAuth1.getUid();
        return uid;
    }

    @Override
    public void updateUserInfo(User user, JSONObject json) throws UserException {
        if (user == null || user.getId() == null) {
            throw new UserException("user is null");
        }
        String temp;
        if ((temp = json.getString("nickname")) != null) {
            user.setNickname(temp);
            temp = null;
        }
        if ((temp = json.getString("bio")) != null) {
            user.setBio(temp);
            temp = null;
        }
        if ((temp = json.getString("headerImg")) != null) {
            user.setHeaderImg(temp);
            temp = null;
        }
        userMapper.updateUser(user);
    }


    private boolean checkNotNullUserAuth(UserAuth userAuth) {
        return !(userAuth.getIdentifier() == null || userAuth.getAuthType() == null);
    }

    private boolean checkExistUserAuth(UserAuth userAuth) {
        return userMapper.checkUserAuth(userAuth.getIdentifier(), userAuth.getAuthType()) != null;
    }
}
