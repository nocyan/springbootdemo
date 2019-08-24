package com.nocyan.springbootdemo.mapper;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true,keyProperty = "id", keyColumn = "id")
    @Insert("insert into user (nickname,header_img,bio,create_time) values (#{nickname},#{headerImg},#{bio},#{createTime})")
    void insertUser(User user);

    @Select("select * from user where id = #{id}")
    @Results({
            @Result(property = "headerImg",column = "header_img"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time")
    })
    User selectUser(long id);

    @Insert("insert into user_auth (uid,auth_type,identifier,credential,create_time) values (#{uid},#{authType},#{identifier},#{credential},#{createTime})")
    void insertUserAuth(UserAuth userAuth);

    @Select("select * from user_auth where identifier = #{identifier} and credential=#{credential} and auth_type=#{authType}")
    UserAuth selectUserAuth(@Param("identifier") String identifier, @Param("authType") Integer authType, @Param("credential") String credential);

    @Select("select * from user_auth where identifier = #{identifier} and auth_type=#{authType}")
    @Results({
            @Result(property = "authType",column = "auth_type"),
            @Result(property = "createTime",column = "create_time"),
            @Result(property = "updateTime",column = "update_time")
    })
    UserAuth checkUserAuth(@Param("identifier") String identifier, @Param("authType") Integer authType);

}
