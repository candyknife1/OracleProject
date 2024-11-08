package com.lzw.mapper;

import com.lzw.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select({"select * from tb_user where username = #{username} and password = #{password}"})
    User select(@Param("username") String var1, @Param("password") String var2);

    @Select({"select * from tb_user where username = #{username}"})
    User selectByUsername(String var1);

    @Insert({"insert into tb_user values(null,#{username},#{password})"})
    void add(User var1);

    @Insert({"update tb_user set username = #{updateName} where username = #{username}"})
    void updateName(@Param("username") String var1, @Param("updateName") String var2);

    @Insert({"update tb_user set password = #{password} where username = #{username}"})
    void updatePassword(@Param("username") String var1, @Param("password") String var2);
}

