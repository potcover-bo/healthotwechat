package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 身体指数form
 */
@Data
public class BodyDataForm {

    @NotEmpty(message = "phone不能为空")
    private String phone;

    @NotEmpty(message = "体重不能为空")
    private String weight;

    @NotEmpty(message = "运动步数不能为空")
    private String todayStepCount;
}
