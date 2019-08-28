package com.nocyan.springbootdemo.controller;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.service.CookieService;
import com.nocyan.springbootdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    @Autowired
    private CookieService cookieService;
    @Autowired
    private UserService userService;

    private final String DEFAULT_HEADER_IMG = "img/default_headimg.jpg";
    private final String DEFAULT_BIO = "无个人介绍";

    @Value("${github.client.id}")
    private String clientId;

    @GetMapping("/")
    public String indexView(Model model, HttpServletRequest request) {
        headerSupport(model, request);
        return "index";
    }

    @GetMapping("/login")
    public String loginView(Model model, HttpServletRequest request) {
        if (cookieService.getUserFromCookie(request) != null) {
            return "redirect:/";
        }
        model.addAttribute("githubHref", "https://github.com/login/oauth/authorize?client_id=" + clientId);
        return "login";
    }

    @GetMapping("/signup")
    public String signupView(HttpServletRequest request) {
        if (cookieService.getUserFromCookie(request) != null) {
            return "redirect:/";
        }
        return "signup";
    }

    @GetMapping("/themepublish")
    public String themepublishView(Model model, HttpServletRequest request) {
        headerSupport(model, request);
        return "themepublish";
    }

    @GetMapping("/users/{uid}")
    public String userInfoView(@PathVariable String uid, Model model, HttpServletRequest request) {
        headerSupport(model, request);
        Long id;
        try {
            id = Long.valueOf(uid);
        } catch (NumberFormatException e) {
            return "404error";
        }
        User user = userService.getUser(id);
        if (user == null) {
            return "404error";
        }
        String headerImg = user.getHeaderImg();
        String bio = user.getBio();
        model.addAttribute("headimg", headerImg == null ? DEFAULT_HEADER_IMG : headerImg);
        model.addAttribute("nickname", user.getNickname());
        model.addAttribute("bio", bio == null ? DEFAULT_BIO : bio);
        return "userinfo";
    }

    private void headerSupport(Model model, HttpServletRequest request) {
        model.addAttribute("islogin", false);
        User user = cookieService.getUserFromCookie(request);
        if (user != null) {
            model.addAttribute("name", user.getNickname());
            model.addAttribute("islogin", true);
        }
    }
}
