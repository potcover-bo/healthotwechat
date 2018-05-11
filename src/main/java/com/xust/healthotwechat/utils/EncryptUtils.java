package com.xust.healthotwechat.utils;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.util.Base64;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 加密工具包
 */
public class EncryptUtils {

    /**
     * 加密
     * @param source
     * @return
     */
    public static String encrypt(String source){
        BASE64Encoder base64Encoder = new BASE64Encoder();
        return  base64Encoder.encode(source.getBytes());
    }
}
