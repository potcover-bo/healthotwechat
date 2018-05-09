package com.xust.healthotwechat.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血糖数据dto
 */
@Data
public class BloodSugarDto {

    /**血糖值*/
    private String bloodSugarValue;

    /**测量时间*/
    private String measureTime;
}
