package com.xust.healthotwechat.wechat.model;

import lombok.Data;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * access_token实体类
 */
@Data
public class AccessTokenModel {

    private String access_token;

    private String expires_in;
}
