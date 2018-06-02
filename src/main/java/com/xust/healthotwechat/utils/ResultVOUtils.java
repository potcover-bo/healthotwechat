package com.xust.healthotwechat.utils;

import com.xust.healthotwechat.VO.ResultVO;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 返回数据工具包
 */
public class ResultVOUtils {


    /**
     * 成功响应返回  带参数
     * @param object
     * @return
     */
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(0);
        resultVO.setMessage("成功");
        resultVO.setData(object);
        return resultVO;
    }

    /**
     * 成功响应返回  不带参数
     * @return
     */
    public static ResultVO success(){
        return success(null);
    }


    /**
     * 成功响应返回  带参数
     * @param object
     * @return
     */
    public static ResultVO success(Object object,String message){
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(0);
        resultVO.setMessage(message);
        resultVO.setData(object);
        return resultVO;
    }
    /**
     * 成功响应返回  带参数
     * @param object
     * @return
     */
    public static ResultVO success(Integer code,Object object,String message){
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(object);
        return resultVO;
    }



    /**
     * 失败的响应返回 加跳转的url
     * @param code
     * @param message
     * @return
     */
    public static <T> ResultVO error(Integer code,String message,T data){
        ResultVO resultVO = new ResultVO();

        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(data);
        return resultVO;
    }

    /**
     * 失败响应返回
     * @param code
     * @param message
     * @return
     */
    public static  ResultVO error(Integer code,String message){

        return error(code,message,null);
    }


}
