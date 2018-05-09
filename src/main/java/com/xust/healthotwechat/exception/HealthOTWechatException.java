package com.xust.healthotwechat.exception;

/**
 * Created by evildoerdb_ on 2018/5/3
 *
 *
 * 全局通用处理异常
 */
public class HealthOTWechatException extends RuntimeException {


    private String code;

    private String message;


    public HealthOTWechatException() { }

    public HealthOTWechatException(String code,String message){
        super(message);
        this.code = code;
    }
}
