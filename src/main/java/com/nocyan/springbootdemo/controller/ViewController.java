package com.nocyan.springbootdemo.controller;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ViewController {
    @Autowired
    private ViewService viewService;

    @Value("${github.client.id}")
    private String clientId;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        model.addAttribute("islogin", false);
        User user = viewService.getUserFromCookie(request);
        if (user != null) {
            model.addAttribute("name", user.getNickname());
            model.addAttribute("islogin", true);
        }
        viewService.removeUser(request);
        return "index";
    }

    @GetMapping("/login")
    public String loginView(Model model, HttpServletRequest request) {
        if (viewService.getUserFromCookie(request) != null) {
            viewService.removeUser(request);
            return "redirect:/";
        }
        model.addAttribute("githubHref", "https://github.com/login/oauth/authorize?client_id=" + clientId);
        return "login";
    }

    @GetMapping("/signup")
    public String signupView(HttpServletRequest request) {
        if (viewService.getUserFromCookie(request) != null) {
            viewService.removeUser(request);
            return "redirect:/";
        }
        return "signup";
    }

}
