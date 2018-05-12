package com.xust.healthotwechat.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 睡眠表
 */

@Data
public class Sleeping implements Serializable {


    private static final long serialVersionUID = -3448727060074439078L;
    /**id*/
    private Integer id;

    /**openid 关联用户*/
    private String phone;

    /**午睡时间 单位为小时*/
    private String noonTime;

    /**夜晚睡眠时间 单位为小时*/
    private String nightTime;

    /**创建时间*/
    private Date createTime;
}
