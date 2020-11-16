package com.raptor.springboot_security_jsp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.raptor.springboot_security_jsp.mapper")
public class SpringbootSecurityJspApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSecurityJspApplication.class, args);
    }

}
