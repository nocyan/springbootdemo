package com.nocyan.springbootdemo.service.impl;

import com.nocyan.springbootdemo.exception.FileException;
import com.nocyan.springbootdemo.service.FileService;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileServiceImpl implements FileService {

    private final String IMG_PATH=System.getProperty("user.dir")+"/src/main/resources/static/img/userHeader/";
    @Override
    public String uploadUserHeader(MultipartFile file) throws FileException {
        if(file.isEmpty()){
            throw new FileException("file is empty");
        }
        String fileName=getMd5FileName();
        File dest=new File(IMG_PATH+fileName+".jpg");
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private String getMd5FileName(){
        String salt=String.valueOf(Math.random());
        byte[] base=(System.currentTimeMillis()+"/"+salt).getBytes();
        return DigestUtils.md5DigestAsHex(base);
    }
}
