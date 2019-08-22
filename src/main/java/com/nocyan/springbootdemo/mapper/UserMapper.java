package com.nocyan.springbootdemo.mapper;

import com.nocyan.springbootdemo.pojo.User;
import com.nocyan.springbootdemo.pojo.UserAuth;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true,keyProperty = "id", keyColumn = "id")
    @Insert("insert into user (nickname,header_img,bio) values (#{nickname},#{headerImg},#{bio})")
    void insertUser(User user);
    @Select("select * from user where id = #{id}")
    User selectUser(long id);

    @Insert("insert into user_auth (uid,auth_type,identifier,credential) values (#{uid},#{authType},#{identifier},#{credential})")
    void insertUserAuth(UserAuth userAuth);

    @Select("select * from user_auth where identifier = #{identifier}")
    UserAuth selectUserAuth(String identifier);
}
