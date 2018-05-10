package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.wechat.WechatUtils;
import com.xust.healthotwechat.wechat.model.AccessTokenModel;
import com.xust.healthotwechat.wechat.model.MenuCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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

    @ResponseBody
    @RequestMapping("/config")
    public String config(){

        /**获取accesstoken*/
        AccessTokenModel accessTokenModel =wechatUtils.getAccessToken();

        /**创建菜单*/
        MenuCreateModel menu = wechatUtils.createMenu(accessTokenModel.getAccess_token());

        if(menu.getErrcode().equals("0")){
            return "哈哈哈 成功了";
        }
        return "23333333333";
    }

    @Override
    public void run(ApplicationArguments applicationArguments) throws Exception {
        System.out.println("欢迎使用健康管理项目");
        System.out.println("项目启动了。。。。哈哈哈");
    }
}
