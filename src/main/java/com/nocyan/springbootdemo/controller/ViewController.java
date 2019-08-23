package com.nocyan.springbootdemo.controller;

import com.nocyan.springbootdemo.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
public class ViewController {
    @Autowired
    private ViewService viewService;

    @Value("${github.client.id}")
    private String clientId;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("islogin",false);
        Cookie[] cookies = request.getCookies();
        String identifier = null;
        String nickname = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (nickname != null && identifier != null) break;

                if (Objects.equals(cookie.getName(), "identifier")) {
                    identifier = cookie.getValue();
                }
                if (Objects.equals(cookie.getName(), "nickname")) {
                    nickname = cookie.getValue();
                }
            }
        }
        if (identifier != null){
            model.addAttribute("name", nickname == null ? identifier : nickname);
            model.addAttribute("islogin",true);
        }

        return "index";
    }

    @GetMapping("/login")
    public String loginView(Model model,HttpServletRequest request) {
        if(viewService.checkLogin(request))
            return "redirect:/";
        model.addAttribute("githubHref", "https://github.com/login/oauth/authorize?client_id=" + clientId);
        return "login";
    }

    @GetMapping("/signup")
    public String signupView(HttpServletRequest request){
        if(viewService.checkLogin(request))
            return "redirect:/";
        return "signup";
    }

}
