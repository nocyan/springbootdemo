package com.nocyan.springbootdemo.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Theme {
    private Long id;
    private Long uid;
    private String title;
    private String description;
    private Integer replyCount;
    private Long createTime;
    private Timestamp updateTime;

    public Theme() {
    }

    public Theme(Long uid, String title, String description, Integer replyCount) {
        this.uid = uid;
        this.title = title;
        this.description = description;
        this.replyCount = replyCount;
    }

}
