package com.xust.healthotwechat.VO;

import lombok.Data;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 请求返回通用对象
 */

@Data
public class ResultVO<T> {

    /**返回状态码*/
    private Integer code;

    /**返回信息*/
    private String message;

    /**返回数据*/
    private T data;
}
