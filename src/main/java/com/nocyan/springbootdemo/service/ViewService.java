package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface ViewService {
    User getUserFromCookie(HttpServletRequest request);
    void removeUser(HttpServletRequest request);
}
