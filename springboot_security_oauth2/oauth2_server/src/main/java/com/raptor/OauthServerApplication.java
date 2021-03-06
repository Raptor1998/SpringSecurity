package com.raptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor
 * @date 2020/11/14 16:12
 */
@SpringBootApplication
@MapperScan("com.raptor.mapper")
public class OauthServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthServerApplication.class,args);
    }
}
