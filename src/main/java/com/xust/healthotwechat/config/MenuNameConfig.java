package com.xust.healthotwechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by evildoerdb_ on 2018/5/12
 *
 * 菜单名字配置
 */
@Component
@Data
@ConfigurationProperties(prefix = "menuName")
public class MenuNameConfig {

    /**
     *
     * 第一个一级菜单名字
     */
    private String firstLevelName;

    /**
     * 第一个一级级菜单名字
     */
    private String secondeLevelName;


    /**
     * 第三个一级菜单名字
     */
    private String thirdLevelName;


    /**
     * 第一个二级第一个子菜单名字
     */
    private String firstLevelFirstName;

    /**
     * 第一个二级第二个子菜单名字
     */
    private String firstLevelSecondName;

    /**
     * 第一个二级第三个子菜单名字
     */
    private String firstLevelThirdName;

    /**
     *  第一个二级第四个子菜单名字
     */
    private String firstLevelFourthName;

    /**
     * 第一个二级第五个子菜单名字
     */
    private String firstLevelFifthName;


    /**
     * 第二个二级第一个子菜单名字
     */
    private String secondeLevelFirstName;

    /**
     * 第二个二级第二个子菜单名字
     */
    private String secondeLevelSecondName;

    /**
     * 第二个二级第三个子菜单名字
     */
    private String secondeLevelThirdName;

    /**
     * 第二个二级第四个子菜单名字
     */
    private String secondeLevelFourthName;

    /**
     * 第二个二级第五个子菜单名字
     */
    private String secondeLevelFifthName;


    /**
     * 第三个二级第一个子菜单名字
     */
    private String thirdLevelFirstName;

    /**
     * 第三个二级第二个子菜单名字
     */
    private String thirdLevelSecondName;







}
