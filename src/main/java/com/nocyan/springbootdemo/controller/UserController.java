package com.nocyan.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.Util.ControllerUtil;
import com.nocyan.springbootdemo.exception.UserException;
import com.nocyan.springbootdemo.mapper.UserMapper;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public String signUp(@RequestBody JSONObject json, HttpServletResponse response){
        JSONObject responseJson=new JSONObject();
        UserAuth userAuth= json.getObject("userAuth",UserAuth.class);
        User user=new User();
        user.setNickname(userAuth.getIdentifier());
        userAuth.setUid(userService.registerUser(user));
        System.out.println(userAuth);
        try {
            userService.registerUserAuth(userAuth);
        } catch (UserException e) {
            return ControllerUtil.setErrorMessage(responseJson,e.getMessage()).toJSONString();
        }
        Cookie idCookie = new Cookie("identifier", userAuth.getIdentifier());
        idCookie.setPath("/");
        response.addCookie(idCookie);
        Cookie nameCookie = new Cookie("nickname", user.getNickname());
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
        return ControllerUtil.setSuccessMessage(responseJson,"sign up",user).toJSONString();
    }

}
