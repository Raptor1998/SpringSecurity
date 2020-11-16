package com.raptor.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.controller
 * @date 2020/11/12 20:59
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @RequestMapping("/findAll")
    public String getAll(){
        return "查询列表成功！！！oath2";
    }
}
