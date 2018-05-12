package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血压form
 */
@Data
public class BloodPressureForm implements Serializable {


    private static final long serialVersionUID = 5445059021977552227L;


    @NotEmpty(message = "phone不能为空")
    private String phone;

    @NotEmpty(message = "高压不能为空")
    private String highPressure;

    @NotEmpty(message = "低压不能为空")
    private String lowPressure;

    @NotEmpty(message = "吃药不能为空")
    private String mealCondition;

    @NotEmpty(message = "服药不能为空")
    private String medicineCondition;

    @NotEmpty(message = "是否保存健康档案不能为空")
    private String saveHealthRecord;
}
