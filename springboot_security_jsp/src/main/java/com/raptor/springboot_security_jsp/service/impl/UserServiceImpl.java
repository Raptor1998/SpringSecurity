package com.raptor.springboot_security_jsp.service.impl;

import com.raptor.springboot_security_jsp.mapper.UserMapper;
import com.raptor.springboot_security_jsp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.springboot_security_jsp.service.impl
 * @date 2020/11/3 21:20
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userMapper.findByName(s);
    }
}
