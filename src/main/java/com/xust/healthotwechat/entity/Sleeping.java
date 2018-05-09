package com.xust.healthotwechat.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 睡眠表
 */

@Data
public class Sleeping {

    /**id*/
    private Integer id;

    /**openid 关联用户*/
    private String openid;

    /**午睡时间 单位为小时*/
    private String noonTime;

    /**夜晚睡眠时间 单位为小时*/
    private String nightTime;

    /**创建时间*/
    private Date createTime;
}
