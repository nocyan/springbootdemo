package com.nocyan.springbootdemo.service.impl;

import com.nocyan.springbootdemo.service.ViewService;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class ViewServiceImpl implements ViewService {
    @Override
    public boolean checkLogin(HttpServletRequest request) {
        boolean isLogin=false;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (Objects.equals(cookie.getName(), "identifier")) {
                    isLogin=true;
                    break;
                }
            }
        }
        return isLogin;
    }
}
