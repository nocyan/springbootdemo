package com.nocyan.springbootdemo.controller;

import com.nocyan.springbootdemo.pojo.OAuthUser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Value("${github.client.id}")
    private String clientId;

    @GetMapping("/")
    public String index(Model model, HttpServletRequest request) {
        OAuthUser oAuthUser = (OAuthUser) request.getSession().getAttribute("user");
        if (oAuthUser != null)
            model.addAttribute("name", oAuthUser.getName());
        model.addAttribute("githubHref", "https://github.com/login/oauth/authorize?client_id=" + clientId);
        return "index";
    }
}
