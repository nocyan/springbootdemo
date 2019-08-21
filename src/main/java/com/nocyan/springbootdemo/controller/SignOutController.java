package com.nocyan.springbootdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SignOutController {

    @GetMapping("/signout")
    public String signOut(HttpServletRequest request){
        request.getSession().removeAttribute("user");
        return "redirect:/";
    }
}
