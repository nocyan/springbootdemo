package com.nocyan.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.Util.ControllerUtil;
import com.nocyan.springbootdemo.exception.ThemeException;
import com.nocyan.springbootdemo.pojo.Theme;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.service.CookieService;
import com.nocyan.springbootdemo.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ThemeController {
    @Autowired
    private CookieService cookieService;

    @Autowired
    private ThemeService themeService;

    @PostMapping("/themes")
    public String addTheme(@RequestBody JSONObject json, HttpServletRequest request) {
        JSONObject responseJson = new JSONObject();
        try {
            User user = cookieService.getUserFromCookie(request);
            Theme theme = JSON.parseObject(json.toJSONString(), Theme.class);
            themeService.checkLoginAndTheme(user, theme);
            Long themeId = themeService.addTheme(theme);
            return ControllerUtil.setSuccessMessage(responseJson, "publish theme", themeId);
        } catch (ThemeException e) {
            return ControllerUtil.setErrorMessage(responseJson, e.getMessage());
        }
    }
}
