package com.nocyan.springbootdemo.Util;

import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.pojo.User;

public class ControllerUtil {
    private ControllerUtil(){}
    public static JSONObject setErrorMessage(JSONObject json, String message) {
        json.put("status", "false");
        json.put("message", message);
        return json;
    }

    public static JSONObject setSuccessMessage(JSONObject json, String operation, User user) {
        json.put("status", "success");
        json.put("operation", operation);
        json.put("user",user);
        return json;
    }
}
