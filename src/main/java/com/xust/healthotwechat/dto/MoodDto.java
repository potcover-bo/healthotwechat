package com.xust.healthotwechat.dto;

import lombok.Data;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 心情dto
 *
 */
@Data
public class MoodDto {

//    /**早上心情*/
//    private String morningMood;
//
//    /**中午心情*/
//    private String noonMood;
//
//    /**晚上心情*/
//    private String nightMood;
//
//    /**创建时间*/
//    private String createTime;

    private Integer good;

    private Integer general;

    private Integer poor;
}
