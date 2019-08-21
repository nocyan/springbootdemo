package com.nocyan.springbootdemo.controller;

import com.nocyan.springbootdemo.pojo.GithubUser;
import com.nocyan.springbootdemo.provider.GithubOAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GithubOAuthController {
    @Autowired
    private GithubOAuthProvider githubOAuthProvider;

    @GetMapping("/callback")
    public String githubCallback(@RequestParam(name="code") String code , HttpServletRequest request){
        String accessToken=githubOAuthProvider.getAccessToken(code);
        System.out.println(accessToken);
        if(accessToken==null||"".equals(accessToken)){
            throw new RuntimeException("github server error");
        }
        GithubUser githubUser=githubOAuthProvider.getGithubUser(accessToken);
        if(githubUser!=null){
            //登录成功
            request.getSession().setAttribute("user",githubUser);
            return "redirect:/";
        }else{
            //登录失败
            return "redirect:/";
        }
    }
}
