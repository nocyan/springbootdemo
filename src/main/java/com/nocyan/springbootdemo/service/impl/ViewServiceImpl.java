package com.nocyan.springbootdemo.service.impl;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.service.ViewService;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Component
public class ViewServiceImpl implements ViewService {
    @Override
    public User getUserFromCookie(HttpServletRequest request) {
        User user=(User) request.getSession().getAttribute("user");
        return user;
    }

    @Override
    public void removeUser(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }
}
