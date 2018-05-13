package com.xust.healthotwechat.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.xust.healthotwechat.config.MenuNameConfig;
import com.xust.healthotwechat.config.MenuUrlConfig;
import com.xust.healthotwechat.utils.SmsUtils;
import com.xust.healthotwechat.wechat.WechatUtils;
import com.xust.healthotwechat.wechat.model.AccessTokenModel;
import com.xust.healthotwechat.wechat.model.MenuCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 初始化菜单controller
 */
@Component
@Order(value = 1)
public class InitMenuController implements ApplicationRunner {



    @Autowired
    private WechatUtils wechatUtils;

    @Autowired
    private MenuNameConfig menuNameConfig;

    @Autowired
    private MenuUrlConfig menuUrlConfig;

    @ResponseBody
    @RequestMapping("/config")
    public void config(){


        /**获取accesstoken*/
        AccessTokenModel accessTokenModel =wechatUtils.getAccessToken();

        /**创建菜单*/
        MenuCreateModel menu = wechatUtils.createMenu(accessTokenModel.getAccess_token(),menuNameConfig,menuUrlConfig);

        if(menu.getErrcode().equals("0")){
            System.out.println("哈哈哈 成功了");
        }else {
            System.out.println("23333333333");
        }

    }

    @Override
    public void run(ApplicationArguments applicationArguments) {

        System.out.println("欢迎使用健康管理项目");
        System.out.println("项目启动了。。。。哈哈哈");
//
//        try {
//            SendSmsResponse sendSmsResponse = SmsUtils.sendSms("18789449429","123456");
//            System.out.println("code = " +sendSmsResponse.getCode());
//            System.out.println("message = " +sendSmsResponse.getMessage());
//            System.out.println("requestid = " + sendSmsResponse.getRequestId());
//            System.out.println("bizid = " + sendSmsResponse.getBizId());
//            System.out.println("发送成功");
//        } catch (ClientException e) {
//            System.out.println("发送失败");
//            e.printStackTrace();
//        }


    }
}
