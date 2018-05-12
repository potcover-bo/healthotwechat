package com.xust.healthotwechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by evildoerdb_ on 2018/5/12
 *
 * 菜单url配置
 */

@Component
@ConfigurationProperties(prefix = "menuUrl")
@Data
public class MenuUrlConfig {

    /**
     *
     * 第一个一级菜单Url
     */
    //private String firstLevelUrl;

    /**
     * 第一个一级级菜单Url
     */
    //private String secondeLevelUrl;


    /**
     * 第三个一级菜单Url
     */
    //private String thirdLevelUrl;


    /**
     * 第一个二级第一个子菜单Url
     */
    private String firstLevelFirstUrl;

    /**
     * 第一个二级第二个子菜单Url
     */
    private String firstLevelSecondUrl;

    /**
     * 第一个二级第三个子菜单Url
     */
    private String firstLevelThirdUrl;

    /**
     *  第一个二级第四个子菜单Url
     */
    private String firstLevelFourthUrl;

    /**
     * 第一个二级第五个子菜单Url
     */
    private String firstLevelFifthUrl;


    /**
     * 第二个二级第一个子菜单Url
     */
    private String secondeLevelFirstUrl;

    /**
     * 第二个二级第二个子菜单Url
     */
    private String secondeLevelSecondUrl;

    /**
     * 第二个二级第三个子菜单Url
     */
    private String secondeLevelThirdUrl;

    /**
     * 第二个二级第四个子菜单Url
     */
    private String secondeLevelFourthUrl;

    /**
     * 第二个二级第五个子菜单Url
     */
    private String secondeLevelFifthUrl;


    /**
     * 第三个二级第一个子菜单Url
     */
    private String thirdLevelFirstUrl;

    /**
     * 第三个二级第二个子菜单Url
     */
    private String thirdLevelSecondUrl;
}
