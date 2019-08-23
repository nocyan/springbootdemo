package com.nocyan.springbootdemo.enums;

import com.nocyan.springbootdemo.DTO.OAuthDTOimpl.GithubUserDTO;
import com.nocyan.springbootdemo.provider.GithubOAuthProvider;

public enum AuthTypeEnum {
    GITHUB(128+1,"github", GithubOAuthProvider.class),USERNAME(0+1,"username",null);

    private int sign;
    private String name;
    private Class providerClazz;

    AuthTypeEnum(int sign,String name,Class providerClazz){this.sign=sign;this.name=name;this.providerClazz=providerClazz;}

    public static AuthTypeEnum getType(int type){
        switch (type){
            case 128+1: return AuthTypeEnum.GITHUB;
            case 0+1: return AuthTypeEnum.USERNAME;
            default:throw new IllegalArgumentException("cannot find auth type");
        }
    }

    public static boolean checkType(int type){
        switch (type){
            case 128+1: return true;
            case 0+1: return true;
            default:return false;
        }
    }

    public static boolean isOAuth(AuthTypeEnum authTypeEnum){
        return !((128 & authTypeEnum.getSign())==0);
    }

    public static boolean isOAuth(Integer integer){
        return !((128 & integer)==0);
    }

    public int getSign() {
        return sign;
    }

    public void setSign(int sign) {
        this.sign = sign;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getProviderClazz() throws Exception {
        if(!isOAuth(this)){throw new Exception("illegal auth type");}
        return providerClazz;
    }

    public void setProviderClazz(Class providerClazz) {
        this.providerClazz = providerClazz;
    }
}
