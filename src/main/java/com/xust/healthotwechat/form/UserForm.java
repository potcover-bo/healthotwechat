package com.xust.healthotwechat.form;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 用户form表单
 */
@Data
public class UserForm implements Serializable {


    private static final long serialVersionUID = 4265991455296214515L;

    @NotEmpty(message = "手机号码不能为空")
    private String phone;

    @NotEmpty(message = "身份证号码不能为空")
    private String idcard;

    @NotEmpty(message = "验证码不能为空")
    private String validateCode;

    @NotEmpty(message = "用户姓名不能为空")
    private String username;

    @NotEmpty(message = "用户密码不能为空")
    private String password;

    @NotEmpty(message = "用户性别不能为空")
    private String sex;

    @NotEmpty(message = "用户年龄不能为空")
    private String age;

    @NotEmpty(message = "监护人手机号码不能为空")
    private String custodyPhone;

    @NotEmpty(message = "与监护人关系不能为空")
    private String custodyRelationship;

}
