package com.xust.healthotwechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 微信urlconfig
 */
@Component
@ConfigurationProperties(prefix = "wechat.url")
@Data
public class WechatUrlConfig {


    /**获取accesstoken的url*/
    private String accesstokenUrl;

    /**创建菜单的url*/
    private String menuCreateUrl;

    /**创建openid的url*/
    private String openidUrl;
}
