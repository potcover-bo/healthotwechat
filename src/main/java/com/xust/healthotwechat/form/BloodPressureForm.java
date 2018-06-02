package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血压form
 */
@Data
public class BloodPressureForm implements Serializable {


    private static final long serialVersionUID = 5445059021977552227L;



    private String phone;

    @NotEmpty(message = "高压不能为空")
    @Size(max = 3,min = 2,message = "请输入正确的高压值")
    private String highPressure;

    @NotEmpty(message = "低压不能为空")
    @Size(max = 3,min = 2,message = "请输入正确的低压值")
    private String lowPressure;

    @NotEmpty(message = "吃药不能为空")
    private String mealCondition;

    @NotEmpty(message = "服药不能为空")
    private String medicineCondition;

    private String saveHealthRecord;
}
