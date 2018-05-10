package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.wechat.WechatUtils;
import com.xust.healthotwechat.wechat.model.AccessTokenModel;
import com.xust.healthotwechat.wechat.model.MenuCreateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 测试controller
 */
@Controller
@RequestMapping("/test")
public class TestController {



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
}
