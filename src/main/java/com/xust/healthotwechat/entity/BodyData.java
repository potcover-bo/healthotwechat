package com.xust.healthotwechat.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 身体数据
 *
 */
@Data
public class BodyData {

    /**id*/
    private Integer id;

    /** openid 关联用户*/
    private String openid;

    /** 体重  单位为千克*/
    private String weight;

    /**  运动步数*/
    private String todayStepCount;

    /**创建时间*/
    private Date createTime;
}
