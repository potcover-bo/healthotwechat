package com.xust.healthotwechat.wechat;

import com.google.gson.Gson;
import com.xust.healthotwechat.config.WechatConfig;
import com.xust.healthotwechat.config.WechatUrlConfig;
import com.xust.healthotwechat.utils.HttpUtils;
import com.xust.healthotwechat.wechat.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 微信工具类
 */
@Slf4j
@Service
public class WechatUtils {

    @Autowired
    private  WechatUrlConfig wechatUrlConfig;

    @Autowired
    private  WechatConfig wechatConfig;

    /**
     * 获取accesstoken
     * @param
     * @return
     */
    public AccessTokenModel getAccessToken(){
        return HttpUtils.doGet(wechatUrlConfig.getAccesstokenUrl().replace("APPID",wechatConfig.getAppid())
                .replace("APPSECRET",wechatConfig.getSecret()),AccessTokenModel.class);
    }

    /**
     * 创建菜单
     * @return
     */
    public MenuCreateModel createMenu(String token){
        Gson gson = new Gson();
        /**初始化菜单*/
        String json = gson.toJson(initMenu());
        return  HttpUtils.doPost(wechatUrlConfig.getMenuCreateUrl().replace("ACCESS_TOKEN",token),json,MenuCreateModel.class);
    }




    /**
     * 初始化菜单
     */
    public  WechatMenu initMenu(){
        WechatMenu menu = new WechatMenu();

        List<Button> sub_button = new ArrayList<>();

        /**一级菜单名字*/
        Button button1 = new Button();
        button1.setName("健康数据");

        //一级子菜单列表
        List<Button> sub_button1 = new ArrayList<>();

        /**一级子菜单*/
        ViewButton button11 = generateViewButton("录入血压","view","http://dou.natapp1.cc/index.html");

        ViewButton button12 = generateViewButton("录入血糖","view","http://dou.natapp1.cc/index.html");

        ViewButton button13 = generateViewButton("录入心情","view","http://dou.natapp1.cc/index.html");

        ViewButton button14 = generateViewButton("身体指数","view","http://dou.natapp1.cc/index.html");

        ViewButton button15 = generateViewButton("睡眠和服药","view","http://dou.natapp1.cc/index.html");


        /**加入一级子菜单列表*/
        sub_button1.add(button11);
        sub_button1.add(button12);
        sub_button1.add(button13);
        sub_button1.add(button14);
        sub_button1.add(button15);


        button1.setSub_button(sub_button1);
        sub_button.add(button1);

        /**********************/
        /**二级菜单名字*/
        Button button2 = new Button();
        button2.setName("历史记录");

        //二级子菜单列表
        List<Button> sub_button2 = new ArrayList<>();

        /**二级子菜单*/
        ViewButton button21 = generateViewButton("血压曲线","view","http://dou.natapp1.cc/index.html");

        ViewButton button22 = generateViewButton("血糖曲线","view","http://www.github.com");

        ViewButton button23 = generateViewButton("心情","view","http://www.baidu.com");

        ViewButton button24 = generateViewButton("身体指数","view","http://www.baidu.com");

        ViewButton button25 = generateViewButton("睡眠","view","http://www.baidu.com");


        /**加入二级子菜单列表*/
        sub_button2.add(button21);
        sub_button2.add(button22);
        sub_button2.add(button23);
        sub_button2.add(button24);
        sub_button2.add(button25);


        button2.setSub_button(sub_button2);
        sub_button.add(button2);


        /************/
        /**三级button名字*/
        Button button3 = new Button();
        button3.setName("个人中心");

        //三级子菜单列表
        List<Button> sub_button3 = new ArrayList<>();
        ViewButton button31 = generateViewButton("我的账号","view","http://dou.natapp1.cc/index.html");
        ClickButton button32 = new ClickButton();
        button32.setName("关于我们");
        button32.setType("click");
        button32.setKey("V1001_GOOD");

        sub_button3.add(button31);
        sub_button3.add(button32);

        button3.setSub_button(sub_button3);

        sub_button.add(button3);

        menu.setButton(sub_button);
        return menu;
    }


    /**
     * 生成ViewButton
     * @param name 名字
     * @param type 类型
     * @param url url
     * @return
     */
    private ViewButton generateViewButton(String name,String type,String url){
        ViewButton  viewButton = new ViewButton();

        viewButton.setName(name);
        viewButton.setType(type);
        viewButton.setUrl(url);

        return viewButton;
    }






}
