package com.nocyan.springbootdemo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @Value("${github.client.id}")
    private String clientId;
    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("name","nocyan");
        model.addAttribute("githubHref","https://github.com/login/oauth/authorize?client_id="+clientId);
        return "index";
    }
}
