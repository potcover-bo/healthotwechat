package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 睡眠form
 */

@Data
public class SleepingForm implements Serializable {


    private static final long serialVersionUID = 5094811170270159511L;

    @NotEmpty(message = "phone不能为空")
    private String phone;

    @NotEmpty(message = "中午睡眠时间不能为空")
    private String noonTime;

    @NotEmpty(message = "夜晚睡眠时间不能为空")
    private String nightTime;
}
