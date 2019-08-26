package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.exception.ThemeException;
import com.nocyan.springbootdemo.pojo.Theme;
import com.nocyan.springbootdemo.pojo.User;

public interface ThemeService {
    void checkLoginAndTheme(User user,Theme theme) throws ThemeException;
    Long addTheme(Theme theme);
}
