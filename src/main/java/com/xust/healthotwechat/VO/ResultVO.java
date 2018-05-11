package com.xust.healthotwechat.VO;

import lombok.Data;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 请求返回通用对象
 */

@Data
public class ResultVO<T> {

    private String code;

    private String message;

    private T data;
}
