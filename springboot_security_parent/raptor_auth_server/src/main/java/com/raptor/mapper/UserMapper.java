package com.raptor.mapper;

import com.raptor.domain.SysUser;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;


/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.springboot_security_jsp.mapper
 * @date 2020/11/3 21:00
 */
public interface UserMapper{


    @Select("select * from sys_user where username = #{username}")
    @Results({
        @Result(id = true, property = "id", column = "id"),
        @Result(property = "roles", column = "id",
                javaType = List.class,
                many = @Many(select = "com.raptor.mapper.RoleMapper.findByUid"))
    })
    SysUser findByName(String username);
}
