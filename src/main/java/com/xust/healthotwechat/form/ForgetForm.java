package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by evildoerdb_ on 2018/6/12
 */
@Data
public class ForgetForm {

    @NotEmpty(message = "手机号码不能为空")
    private String phone;

    @NotEmpty(message = "验证码不能为空")
    private String validateCode;

    @NotEmpty(message = "密码不能为空")
    private String password;
}
