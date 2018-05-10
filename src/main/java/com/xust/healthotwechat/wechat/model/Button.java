package com.xust.healthotwechat.wechat.model;

import lombok.Data;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 微信菜单按钮
 *
 */
@Data
public class Button {

    private String name;

    private String type;

    List<Button> sub_button;
}
