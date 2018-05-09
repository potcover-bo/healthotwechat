package com.xust.healthotwechat.dto;

import lombok.Data;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 睡眠dto
 */
@Data
public class SleepingDto {

    /**午睡时间*/
    private String noonTime;

    /**夜晚睡眠时间*/
    private String nightTime;

    /**录入时间*/
    private String createTime;
}
