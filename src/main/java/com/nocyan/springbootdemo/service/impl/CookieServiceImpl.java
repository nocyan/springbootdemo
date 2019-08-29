package com.nocyan.springbootdemo.service.impl;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.service.CookieService;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class CookieServiceImpl implements CookieService {
    @Override
    public User getUserFromSession(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    @Override
    public void removeUserFromSession(HttpServletRequest request) {
        request.getSession().removeAttribute("user");
    }

}
