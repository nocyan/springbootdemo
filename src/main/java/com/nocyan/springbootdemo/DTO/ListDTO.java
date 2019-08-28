package com.nocyan.springbootdemo.DTO;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ListDTO<T> {
    private Integer num;
    private String content;
    private List<T> list;
}
