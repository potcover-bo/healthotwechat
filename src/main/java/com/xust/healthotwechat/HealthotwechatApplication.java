package com.xust.healthotwechat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;


@SpringBootApplication
@MapperScan(basePackages = "com.xust.healthotwechat.entity")
@ServletComponentScan
public class HealthotwechatApplication {

    public static void main(String[] args) {

        SpringApplication.run(HealthotwechatApplication.class, args);
    }

}
