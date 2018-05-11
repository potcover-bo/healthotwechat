package com.xust.healthotwechat.exception;

import lombok.Getter;
import lombok.ToString;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 全局通用处理异常错误码
 *
 */

@Getter
@ToString
public enum HealthOTWechatErrorCode {

    BLOOD_PRESSURE_ENTRY_ERROE("10000","录入血压出错"),
    DATA_CONVERT_ERROR("10001","数据转换出错"),
    ALREADY_ENTRY_DATA("10002","用户当天已经录入过数据"),
    DATA_NO_EXIST("10003","您没有录入过任何数据"),
    BLOOD_SUGAR_ENTRY_ERROE("10004","录入血糖出错"),
    BODY_DATA_ENTRY_ERROE("10005","录入身体指数出错"),
    SLEEPING_DATA_ENTRY_ERROE("10006","录入睡眠数据出错"),
    MOOD_DATA_ENTRY_ERROE("10007","录入心情数据出错"),
    MEDICINE_DATA_ENTRY_ERROE("10008","录入服药数据出错"),
    USER_REGISTER_ERROE("10009","用户注册出错")
    ;

    private String code;

    private String message;

    HealthOTWechatErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
