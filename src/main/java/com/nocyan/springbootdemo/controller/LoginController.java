package com.nocyan.springbootdemo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.Util.ControllerUtil;
import com.nocyan.springbootdemo.enums.AuthTypeEnum;
import com.nocyan.springbootdemo.exception.UserException;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody JSONObject json,HttpServletResponse response) {
        JSONObject responseJson=new JSONObject();
        UserAuth userAuth= json.getObject("userAuth",UserAuth.class);
        System.out.println(userAuth);
        long uid=userService.verifyUserAuth(userAuth);
        if(uid==-1){
            return ControllerUtil.setErrorMessage(responseJson,"user auth error").toJSONString();
        }
        User user=userService.getUser(uid);
        Cookie idCookie = new Cookie("identifier", userAuth.getIdentifier());
        idCookie.setPath("/");
        response.addCookie(idCookie);
        Cookie nameCookie = new Cookie("nickname", user.getNickname());
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
        return ControllerUtil.setSuccessMessage(responseJson,"sign in",user).toJSONString();
    }

    @DeleteMapping("/login")
    public String signOut(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        //遍历cookies，删除登录信息
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (Objects.equals(cookie.getName(), "nickname")) {
                cookie.setMaxAge(0);
            }
            if (Objects.equals(cookie.getName(), "identifier")) {
                cookie.setMaxAge(0);
            }
        }
        return ControllerUtil.setSuccessMessage(json,"sign out",null).toJSONString();
    }

    @PostMapping("/login/OAuth")
    public String OAuthLogin(@RequestBody JSONObject json, HttpServletResponse response) {
        String identifier = json.getString("identifier");
        String accessToken = json.getString("accessToken");
        Integer authType = json.getInteger("authType");
        JSONObject responseJson = new JSONObject();
        if (identifier == null || accessToken == null || authType == null || !(AuthTypeEnum.checkType(authType) && AuthTypeEnum.isOAuth(authType))) {
            //登录失败
            return ControllerUtil.setErrorMessage(responseJson, "param error").toJSONString();
        }

        //通过accessToken验证用户真实性
        try {
            if (!userService.checkOAuthAccessToken(accessToken, AuthTypeEnum.getType(authType).getProviderClazz())) {
                return ControllerUtil.setErrorMessage(responseJson, "illegal access token").toJSONString();
            }
        } catch (Exception e) {
            return ControllerUtil.setErrorMessage(responseJson, "auth type error").toJSONString();
        }

        //将DTO转化为userAuth
        UserAuth userAuth = null;
        try {
            userAuth = userService.checkAndInitOAuthUserAuth(authType, identifier);
        } catch (UserException e) {
            return ControllerUtil.setErrorMessage(responseJson, "unknown error").toJSONString();
        }
        User user = userService.getUser(userAuth.getUid());
        //设置cookie
        Cookie idCookie = new Cookie("identifier", userAuth.getIdentifier());
        idCookie.setPath("/");
        response.addCookie(idCookie);
        Cookie nameCookie = new Cookie("nickname", user.getNickname());
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
        //设置返回信息
        return ControllerUtil.setSuccessMessage(responseJson,"OAuth sign in",user).toJSONString();
    }


}
