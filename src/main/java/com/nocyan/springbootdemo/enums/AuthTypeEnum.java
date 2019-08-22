package com.nocyan.springbootdemo.enums;

public enum AuthTypeEnum {
    GITHUB(128+1,"github"),USERNAME(0+1,"username");

    private int sign;
    private String name;

    AuthTypeEnum(int sign,String name){this.sign=sign;this.name=name;}

    public static AuthTypeEnum getType(int type){
        switch (type){
            case 128+1: return AuthTypeEnum.GITHUB;
            case 0+1: return AuthTypeEnum.USERNAME;
            default:throw new IllegalArgumentException("cannot find auth type");
        }
    }

    public static boolean isOAuth(AuthTypeEnum authTypeEnum){
        return !((128 & authTypeEnum.getSign())==0);
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
}
