package com.xust.healthotwechat.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 微信配置
 */
@Component
@ConfigurationProperties(prefix = "wechat")
@Data
public class WechatConfig {

    private String appid;

    private String secret;

    private String token;


}
