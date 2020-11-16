package com.raptor;

import com.raptor.config.RsaKeyProperties;
import com.raptor.domain.SysRole;
import com.raptor.domain.SysUser;
import com.raptor.utils.JwtUtils;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor
 * @date 2020/11/8 21:51
 */
public class TokenTest {
    @Test
    public void context() throws Exception {
        RsaKeyProperties rsaKeyProperties=new RsaKeyProperties();
        SysUser sysUser=new SysUser();
        sysUser.setUsername("raptor");
        List<SysRole> list=new ArrayList<>();
        SysRole sysRole = new SysRole();
        sysRole.setId(1);
        sysRole.setRoleName("ROLE_ADMIN");
        sysRole.setRoleDesc("管理员");
        list.add(sysRole);
        sysUser.setRoles(list);
        String token= JwtUtils.generateTokenExpireInMinutes(sysUser, rsaKeyProperties.getPrivateKey(),24*60);
        System.out.println(token);
        System.out.println(JwtUtils.getInfoFromToken(token,rsaKeyProperties.getPublicKey()));
    }
}
