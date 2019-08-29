package com.nocyan.springbootdemo.controller;

import com.alibaba.fastjson.JSONObject;
import com.nocyan.springbootdemo.Util.ControllerUtil;
import com.nocyan.springbootdemo.exception.FileException;
import com.nocyan.springbootdemo.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/user/header_img")
    public String uploadUserHeader(@RequestParam("file") MultipartFile file){
        JSONObject responseJson=new JSONObject();
        try {
            String fileName=fileService.uploadUserHeader(file);
            return ControllerUtil.setSuccessMessage(responseJson,"upload user's header",fileName);
        } catch (FileException e) {
            return ControllerUtil.setErrorMessage(responseJson,e.getMessage());
        }
    }
}
