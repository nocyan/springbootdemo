package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.exception.ThemeException;
import com.nocyan.springbootdemo.pojo.Theme;
import com.nocyan.springbootdemo.pojo.User;

import java.util.List;

public interface ThemeService {
    void checkLoginAndTheme(User user,Theme theme) throws ThemeException;
    Long addTheme(Theme theme);
    Integer getUserThemesCount(Long uid);
    List<Theme> getUserThemes(Long uid);
    List<Theme> getUserThemesByPage(Long uid,Integer page,Integer limit);

}
