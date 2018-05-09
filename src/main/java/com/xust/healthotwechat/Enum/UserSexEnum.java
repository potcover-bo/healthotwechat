package com.xust.healthotwechat.Enum;

import lombok.Getter;

/**
 * Created by evildoerdb_ on 2018/5/6
 *
 * 用户性别枚举
 */
@Getter
public enum UserSexEnum {

    MAN(1,"男性"),
    WOMAN(2,"女性"),
    NO(0,"未知")
    ;

    private Integer sex;

    private String desc;

    UserSexEnum(Integer sex, String desc) {
        this.sex = sex;
        this.desc = desc;
    }
}
