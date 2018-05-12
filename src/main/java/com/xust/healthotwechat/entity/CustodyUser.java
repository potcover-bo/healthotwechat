package com.xust.healthotwechat.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 监护人
 */

@Data
public class CustodyUser implements Serializable {


    private static final long serialVersionUID = -6222960264354512655L;
    /**id*/
    private Integer id;


    /**监护人手机号码*/
    private String phone;

    /**监护人身份证号码*/
    private String idcard;

    /**监护人姓名*/
    private String username;

    /**监护人密码*/
    private String password;

    /**监护人头像*/
    private String headimgurl;

    /**监护人性别性别 1位男性 2位女性 0为未知*/
    private Integer sex;

    /**监护人年龄*/
    private Integer age;

    /**被监护人phone*/
    private String custodyphone;

    /**与被监护人的关系*/
    private String custodyRelationship;

}
