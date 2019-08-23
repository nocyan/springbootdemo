package com.nocyan.springbootdemo.provider;

import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.Util.HttpUtil;
import com.nocyan.springbootdemo.DTO.OAuthDTOimpl.GithubUserDTO;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

@Component
public class GithubOAuthProvider implements OAuthProvider{
    @Autowired
    private HttpUtil httpUtil;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;

    /*
    * 通过github返回的random code取得accessToken
    * */
    public String getAccessToken(String code) {
        MediaType mediaType=MediaType.get("application/json; charset=utf-8");
        String url="https://github.com/login/oauth/access_token";
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("client_id",clientId);
        jsonObject.put("client_secret",clientSecret);
        jsonObject.put("code",code);
        String jsonStr=jsonObject.toJSONString();
        HashMap<String,String> headers=new HashMap<>();
        headers.put("Accept","application/json");
        try {
            String responseStr = httpUtil.post(url,jsonStr,mediaType,headers);
            JSONObject responseJson= JSONObject.parseObject(responseStr);
            return responseJson.getString("access_token");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    * 通过accessToken取得用户信息（json）
    * 将用户信息（json）转化为github用户对象（DTO）
    * */
    public GithubUserDTO getGithubUser(String accessToken){
        String url="https://api.github.com/user";
        HashMap<String,String> headers=new HashMap<>();
        headers.put("Authorization","token "+accessToken);
        try {
            String responseStr=httpUtil.get(url,headers);
            return JSONObject.parseObject(responseStr, GithubUserDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public boolean checkAccessToken(String accessToken) {
        return getGithubUser(accessToken).getLogin()!=null;
    }
}
