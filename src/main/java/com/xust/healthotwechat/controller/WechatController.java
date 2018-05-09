package com.xust.healthotwechat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 微信controller
 */

@RestController
@RequestMapping("/weixin")
@Slf4j
public class WechatController {


    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法");
        log.info("code={}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx4515d9a78b58b900&secret=SECRET&code="+code+"&grant_type=authorization_code";
    }
}
