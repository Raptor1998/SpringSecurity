package com.raptor.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.springboot_security_jsp.controller
 * @date 2020/11/3 17:01
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Secured({"ROLE_ADMIN","ROLE_PRODUCT"})
    @RequestMapping("/findAll")
    public String hello() {
        return "产品列表查询成功！！";
    }
}
