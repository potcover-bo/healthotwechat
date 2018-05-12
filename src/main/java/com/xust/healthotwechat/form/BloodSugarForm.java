package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血糖表单
 */
@Data
public class BloodSugarForm implements Serializable {


    private static final long serialVersionUID = 9004620092969037000L;


    @NotEmpty(message = "phone不能为空")
    private String phone;

    @NotEmpty(message = "血糖不能为空")
    private String bloodSugarValue;

    @NotEmpty(message = "吃饭情况不能为空")
    private String mealCondition;

    @NotEmpty(message = "服药情况不能为空")
    private String medicineCondition;

    @NotEmpty(message = "是否保存健康档案不能为空")
    private String saveHealthRecord;
}
