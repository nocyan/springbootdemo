package com.nocyan.springbootdemo.mapper;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true,keyProperty = "id", keyColumn = "id")
    @Insert("insert into user (nickname,header_img,bio) values (#{nickname},#{headerImg},#{bio})")
    void insertUser(User user);
    @Select("select * from user where id = #{id}")
    User selectUser(long id);

    @Insert("insert into user_auth (uid,auth_type,identifier,credential) values (#{uid},#{authType},#{identifier},#{credential})")
    void insertUserAuth(UserAuth userAuth);

    @Select("select * from user_auth where identifier = #{identifier} and credential=#{credential} and auth_type=#{authType}")
    UserAuth selectUserAuth(@Param("identifier") String identifier, @Param("authType") Integer authType, @Param("credential") String credential);

    @Select("select * from user_auth where identifier = #{identifier} and auth_type=#{authType}")
    UserAuth checkUserAuth(@Param("identifier") String identifier, @Param("authType") Integer authType);

}
