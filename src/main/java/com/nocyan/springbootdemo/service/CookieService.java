package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface CookieService {
    User getUserFromSession(HttpServletRequest request);
    void removeUserFromSession(HttpServletRequest request);
}
