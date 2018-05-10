package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 心情form
 */
@Data
public class MoodForm {

    @NotEmpty(message = "phone不能为空")
    private String phone;

    @NotEmpty(message = "早上心情不能为空")
    private String morningMood;

    @NotEmpty(message = "中午心情不能为空")
    private String noonMood;

    @NotEmpty(message = "晚上心情不能为空")
    private String nightMood;
}
