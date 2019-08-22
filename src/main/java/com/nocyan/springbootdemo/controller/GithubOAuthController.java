package com.nocyan.springbootdemo.controller;

import com.nocyan.springbootdemo.DTO.OAuthDTOimpl.GithubUserDTO;
import com.nocyan.springbootdemo.enums.AuthTypeEnum;
import com.nocyan.springbootdemo.provider.GithubOAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class GithubOAuthController {
    @Autowired
    private GithubOAuthProvider githubOAuthProvider;


    /*
    * github回调url，传递code参数
    * */
    @GetMapping("/callback/github")
    public String githubCallback(@RequestParam(name="code") String code , HttpServletRequest request){
        //通过code取得accessToken
        String accessToken=githubOAuthProvider.getAccessToken(code);
        System.out.println(accessToken);
        if(accessToken==null||"".equals(accessToken)){
            throw new RuntimeException("github server error");
        }
        //通过accessToken取得DTO对象
        GithubUserDTO githubUserDTO =githubOAuthProvider.getGithubUser(accessToken);
        if(githubUserDTO !=null){
            //登录成功
            HttpSession session=request.getSession();
            session.setAttribute("OAuthUser", githubUserDTO);
            session.setAttribute("AuthType", AuthTypeEnum.GITHUB);
            return "redirect:/login/OAuth";
        }else{
            //登录失败
            return "redirect:/login/OAuth";
        }
    }
}
