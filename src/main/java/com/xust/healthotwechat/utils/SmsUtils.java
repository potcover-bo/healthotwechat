package com.xust.healthotwechat.utils;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by evildoerdb_ on 2018/5/6
 * 短信工具包
 */
public class SmsUtils {



    /**
     * 生成六位的短信验证码
     * @return
     */
    public static String verificationCode(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < 6 ; i++){
            sb.append(Math.random()*9);
        }
        return sb.toString();
    }
}
