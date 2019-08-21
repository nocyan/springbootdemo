package com.nocyan.springbootdemo.controller;

import com.nocyan.springbootdemo.pojo.GithubUser;
import com.nocyan.springbootdemo.provider.GithubOAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GithubOAuthController {
    @Autowired
    private GithubOAuthProvider githubOAuthProvider;

    @GetMapping("/callback")
    public void githubCallback(@RequestParam(name="code") String code){
        String accessToken=githubOAuthProvider.getAccessToken(code);
        System.out.println(accessToken);
        if(accessToken==null||"".equals(accessToken)){
            throw new RuntimeException("github server error");
        }
        GithubUser githubUser=githubOAuthProvider.getGithubUser(accessToken);
        System.out.println(githubUser.toString());
    }
}
