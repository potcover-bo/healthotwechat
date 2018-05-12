package com.xust.healthotwechat.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 用户心情
 *
 */

@Data
public class Mood implements Serializable {


    private static final long serialVersionUID = -4734849749611598391L;
    /**id*/
    private Integer id;

    /**phone 关联用户*/
    private String phone;

    /**早上心情 1为良好 2为一般 3为差*/
    private String morningMood;

    /**中午心情 1为良好 2为一般 3为差*/
    private String noonMood;

    /**晚上心情 1为良好 2为一般 3为差*/
    private String nightMood;

    /**创建时间*/
    private Date createTime;
}
