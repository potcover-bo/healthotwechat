package com.xust.healthotwechat.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 身体数据
 *
 */
@Data
public class BodyData implements Serializable {


    private static final long serialVersionUID = -2858400118596121581L;
    /**id*/
    private Integer id;

    /** phone 关联用户*/
    private String phone;

    /** 体重  单位为千克*/
    private String weight;

    /**  运动步数*/
    private String todayStepCount;

    /**创建时间*/
    private Date createTime;
}
