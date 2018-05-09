package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 药物form
 */
@Data
public class MedicineForm {

    @NotEmpty(message = "openid不能为空")
    private String openid;

    private String morningMedicine;

    private String morningNumber;

    private String noonMedicine;

    private String noonNumber;

    private String nightMedicine;

    private String nightNumber;
}
