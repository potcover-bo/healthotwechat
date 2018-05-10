package com.xust.healthotwechat.utils;

import com.google.gson.Gson;
import com.xust.healthotwechat.wechat.model.WechatMenu;
import com.xust.healthotwechat.wechat.WechatUtils;
import org.junit.Test;

/**
 * Created by evildoerdb_ on 2018/5/10
 */
public class WechatUtilsTest {

    @Test
    public void initMenu() {

        WechatUtils wechatUtils = new WechatUtils();
        WechatMenu menu = wechatUtils.initMenu();

        Gson gson = new Gson();
        String json = gson.toJson(menu);

        System.out.println(json);

    }
}