package com.nocyan.springbootdemo.service;

import com.nocyan.springbootdemo.exception.FileException;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String uploadUserHeader(MultipartFile file)throws FileException;
}
