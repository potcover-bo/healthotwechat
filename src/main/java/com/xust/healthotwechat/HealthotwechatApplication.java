package com.xust.healthotwechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
@MapperScan(basePackages = "com.xust.healthotwechat.entity")
public class HealthotwechatApplication {

    public static void main(String[] args) {

        SpringApplication.run(HealthotwechatApplication.class, args);
    }

}
