package com.nocyan.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.DTO.ListDTO;
import com.nocyan.springbootdemo.Util.ControllerUtil;
import com.nocyan.springbootdemo.exception.ThemeException;
import com.nocyan.springbootdemo.pojo.Theme;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.service.CookieService;
import com.nocyan.springbootdemo.service.ThemeService;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/themes/{uid}")
    public String listUserThemes(@PathVariable String uid){
        JSONObject responseJson=new JSONObject();
        Long id;
        try {
            id = Long.valueOf(uid);
        } catch (NumberFormatException e) {
            return ControllerUtil.setErrorMessage(responseJson,"param is illegal");
        }
        List<Theme> themes=new ArrayList<Theme>();
        themes=themeService.getUserThemes(id);
        ListDTO<Theme> listDTO=new ListDTO<>();
        listDTO.setContent("theme");
        listDTO.setList(themes);
        listDTO.setNum(themes.size());
        return ControllerUtil.setSuccessMessage(responseJson,"get user's themes",listDTO);
    }
}
