package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.pojo.User;

import javax.servlet.http.HttpServletRequest;

public interface CookieService {
    User getUserFromCookie(HttpServletRequest request);
}
