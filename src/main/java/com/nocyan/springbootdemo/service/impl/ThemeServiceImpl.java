package com.nocyan.springbootdemo.service.impl;

import com.nocyan.springbootdemo.exception.ThemeException;
import com.nocyan.springbootdemo.mapper.ThemeMapper;
import com.nocyan.springbootdemo.pojo.Theme;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ThemeServiceImpl implements ThemeService {
    @Autowired
    private ThemeMapper themeMapper;

    @Override
    public void checkLoginAndTheme(User user, Theme theme) throws ThemeException {
        if (user == null) {
            throw new ThemeException("you haven't logged in");
        }
        if (theme == null) {
            throw new ThemeException("theme is illegal");
        }
        theme.setUid(user.getId());
        theme.setCreateTime(System.currentTimeMillis());
    }

    @Override
    public Long addTheme(Theme theme) {
        return themeMapper.insertTheme(theme);
    }
}
