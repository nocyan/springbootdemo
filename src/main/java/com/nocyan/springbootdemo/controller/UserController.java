package com.nocyan.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.Util.ControllerUtil;
import com.nocyan.springbootdemo.exception.UserException;
import com.nocyan.springbootdemo.mapper.UserMapper;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.service.CookieService;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private CookieService cookieService;

    @PostMapping("/user")
    public String signUp(@RequestBody JSONObject json, HttpServletResponse response){
        JSONObject responseJson=new JSONObject();
        UserAuth userAuth= json.getObject("userAuth",UserAuth.class);
        try {
            userService.registerUserAuth(userAuth);
        } catch (UserException e) {
            return ControllerUtil.setErrorMessage(responseJson,e.getMessage());
        }
        Cookie idCookie = new Cookie("identifier", userAuth.getIdentifier());
        idCookie.setPath("/");
        response.addCookie(idCookie);
        Cookie nameCookie = new Cookie("auth_type", userAuth.getAuthType().toString());
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
        return ControllerUtil.setSuccessMessage(responseJson,"sign up",userService.getUser(userAuth.getUid()));
    }

    @PutMapping("/user")
    public String modifyInfo(@RequestBody JSONObject json,HttpServletRequest request){
        JSONObject responseJson = new JSONObject();
        User user=cookieService.getUserFromSession(request);
        cookieService.removeUserFromSession(request);
        try {
            userService.updateUserInfo(user,json);
            return ControllerUtil.setSuccessMessage(responseJson,"modify user info",user);
        } catch (UserException e) {
            return ControllerUtil.setErrorMessage(responseJson,e.getMessage());
        }
    }
}
