package com.raptor;

import com.raptor.config.RsaKeyProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author raptor
 * @version V1.0
 * @Package com.raptor.raptor_auth_server
 * @date 2020/11/5 11:34
 */
@SpringBootApplication
@MapperScan("com.raptor.mapper")
@EnableConfigurationProperties(RsaKeyProperties.class)
public class AuthSourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthSourceApplication.class,args);
    }
}
