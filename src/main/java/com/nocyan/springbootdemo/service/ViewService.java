package com.nocyan.springbootdemo.service;

import javax.servlet.http.HttpServletRequest;

public interface ViewService {
    boolean checkLogin(HttpServletRequest request);
}
