package com.nocyan.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.DTO.OAuthDTO;
import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }


    @DeleteMapping("/login")
    @ResponseBody
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
        json.put("status", "success");
        json.put("operation", "sign out");
        return json.toJSONString();
    }

    @GetMapping("/login/OAuth")
    public String OAuthLogin(HttpServletRequest request, HttpServletResponse response) {
        Object object = request.getSession().getAttribute("OAuthUser");
        if (object == null) {
            //登录失败
            return "redirect:/";
        }
        OAuthDTO oAuthDTO = (OAuthDTO) object;
        //将DTO转化为userAuth
        UserAuth userAuth = checkAndInitUserAuth(oAuthDTO);
        User user = userService.getUser(userAuth.getUid());
        Cookie idCookie = new Cookie("identifier", userAuth.getIdentifier());
        idCookie.setPath("/");
        response.addCookie(idCookie);
        Cookie nameCookie = new Cookie("nickname", user.getNickname());
        nameCookie.setPath("/");
        response.addCookie(nameCookie);
        return "redirect:/";
    }

    /*
     * 检查userAuth是否进行过注册，若没有则自动注册
     * */
    private UserAuth checkAndInitUserAuth(OAuthDTO oAuthDTO) {
        UserAuth userAuth = userService.getUserAuth(oAuthDTO.getIdentifier());
        if (userAuth == null)
            userAuth = new UserAuth(null, oAuthDTO.getAuthType().getSign(), oAuthDTO.getIdentifier(), oAuthDTO.getCredential());
        if (userAuth.getUid() == null) {
            User user = new User();
            user.setNickname(userAuth.getIdentifier());
            userAuth.setUid(userService.registerUser(user));
            //注册账号认证
            userService.registerUserAuth(userAuth);
        }
        return userAuth;
    }
}
