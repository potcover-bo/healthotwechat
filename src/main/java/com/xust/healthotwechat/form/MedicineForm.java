package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 药物form
 */
@Data
public class MedicineForm implements Serializable {


    private static final long serialVersionUID = -8206691619650199920L;

    @NotEmpty(message = "phone不能为空")
    private String phone;

    private String morningMedicine;

    private String morningNumber;

    private String noonMedicine;

    private String noonNumber;

    private String nightMedicine;

    private String nightNumber;
}
