package com.xust.healthotwechat.utils;

import com.xust.healthotwechat.VO.AjaxResultVo;

/**
 * Created by evildoerdb_ on 2018/5/12
 *
 * ajax响应的vo
 */
public class AjaxResultVOUtils {


    /**
     * 通用成功响应返回加消息
     * @param message
     * @return
     */
    public static AjaxResultVo success(String message){
        AjaxResultVo ajaxResultVo = new AjaxResultVo();
        ajaxResultVo.setCode(666);
        ajaxResultVo.setUrl("entry.html");
        ajaxResultVo.setMessage(message);
        return ajaxResultVo;
    }

    /**
     * 通用成功响应返回
     * @return
     */
    public static  AjaxResultVo success(){
        return success(null);
    }




    /**
     * 通用成功返回加消息
     * @param message
     * @return
     */
    public static AjaxResultVo success(String url,String message){
        AjaxResultVo ajaxResultVo = new AjaxResultVo();
        ajaxResultVo.setCode(666);
        ajaxResultVo.setUrl(url);
        ajaxResultVo.setMessage(message);
        return ajaxResultVo;
    }

    /**
     * 通用失败返回不加消息
     * @return
     */
    public static AjaxResultVo error(){
        return error(null);
    }


    public static AjaxResultVo error(String url,String message){
        AjaxResultVo ajaxResultVo = new AjaxResultVo();
        ajaxResultVo.setCode(233);
        ajaxResultVo.setUrl(url);
        ajaxResultVo.setMessage(message);
        return ajaxResultVo;
    }


    /**
     * 通用失败返回加消息
     * @param message
     * @return
     */
    public static AjaxResultVo error(String message){
        AjaxResultVo ajaxResultVo = new AjaxResultVo();
        ajaxResultVo.setCode(233);
        ajaxResultVo.setUrl("entry.html");
        ajaxResultVo.setMessage(message);
        return ajaxResultVo;
    }
}
