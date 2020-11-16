package com.raptor.springboot_security_jsp.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.springboot_security_jsp.controller
 * @date 2020/11/3 17:01
 */
@Controller
@RequestMapping("/product")
public class ProductController {

    @Secured({"ROLE_ADMIN","ROLE_PRODUCT"})
    @RequestMapping("/findAll")
    public String hello() {
        return "product-list";
    }
}
