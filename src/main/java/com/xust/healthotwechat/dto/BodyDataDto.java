package com.xust.healthotwechat.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 身体数据dto
 */
@Data
public class BodyDataDto {

    /**体重*/
    private String weight;

    /**当天运动步数*/
    private String todayStepCount;

    /**录入时间*/
    private String createTime;
}
