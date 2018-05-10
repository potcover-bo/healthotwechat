package com.xust.healthotwechat.entity;

import lombok.Data;

import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 服药数据
 */

@Data
public class Medicine {


    /**id*/
    private Integer id;

    /**phone 关联用户*/
    private String phone;

    /**早晨药物名字*/
    private String morningMedicine;

    /**早晨药物数量*/
    private String morningNumber;

    /**中午药物名字*/
    private String noonMedicine;

    /**中午药物数量*/
    private String noonNumber;

    /**晚上药物名字*/
    private String nightMedicine;

    /**晚上药物数量*/
    private String nightNumber;

    /**创建时间*/
    private Date createTime;
}
