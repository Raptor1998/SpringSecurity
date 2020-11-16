package com.raptor.mapper;

import com.raptor.domain.SysRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.springboot_security_jsp.mapper
 * @date 2020/11/3 21:01
 */
public interface RoleMapper {

    @Select("SELECT r.id, r.role_name roleName, r.role_desc roleDesc "
            + "FROM sys_role r, sys_user_role ur "
            + "WHERE r.id=ur.rid AND ur.uid=#{uid}")
    List<SysRole> findByUid(Integer uid);
}
