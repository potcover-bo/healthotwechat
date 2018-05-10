package com.xust.healthotwechat.wechat.model;

import com.xust.healthotwechat.wechat.model.Button;
import lombok.Data;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 点击事件
 */
@Data
public class ClickButton extends Button {

    private String key;
}
