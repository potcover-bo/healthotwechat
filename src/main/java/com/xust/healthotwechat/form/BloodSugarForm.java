package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血糖表单
 */
@Data
public class BloodSugarForm {

    @NotEmpty(message = "openid不能为空")
    private String openid;

    @NotEmpty(message = "血糖不能为空")
    private String bloodSugarValue;

    @NotEmpty(message = "吃饭情况不能为空")
    private String mealCondition;

    @NotEmpty(message = "服药情况不能为空")
    private String medicineCondition;

    @NotEmpty(message = "是否保存健康档案不能为空")
    private String saveHealthRecord;
}
