package com.xust.healthotwechat.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/12
 *
 * ajax返回的vo
 */
@Data
public class AjaxResultVo extends ResultVO implements Serializable {


    private static final long serialVersionUID = -306746388790197118L;

    private String url;
}
