package com.xust.healthotwechat.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血压dto
 *
 */

@Data
public class BloodPressureDto implements Serializable {

    /**高压值*/
    private String highPressure;

    /**低压值*/
    private String lowPressure;

    /**测量时间*/
    private String measureTime;
}
