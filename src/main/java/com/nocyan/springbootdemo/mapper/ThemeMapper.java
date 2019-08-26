package com.nocyan.springbootdemo.mapper;

import com.nocyan.springbootdemo.pojo.Theme;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ThemeMapper {

    @Insert("insert into theme (uid,title,description,create_time) value (#{uid},#{title},#{description},#{createTime})")
    Long insertTheme(Theme theme);

    @Select("select * from theme where id = #{id}")
    @Results({
            @Result(property = "replyCount" ,column = "reply_count"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time")
    })
    Theme selectThemeById(Long id);

    @Select("select * from theme where uid = #{uid}")
    @Results({
            @Result(property = "uid",column = "id",one = @One(select = "com.nocyan.springbootdemo.mapper.UserMapper.selectUser"))
    })
    List<Theme> selectThemeListByUid(Long uid);
}
