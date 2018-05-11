package com.xust.healthotwechat.convert;

import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.form.UserForm;
import com.xust.healthotwechat.utils.EncryptUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;
import org.springframework.stereotype.Service;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 用户数据转换
 */
@Service
public class UserConvertService {

    /**
     * 用户form转换entity
     * @param userForm
     * @return
     */
    public User formToEntity(UserForm userForm) throws BeansException {
        User user = new User();

        BeanUtils.copyProperties(userForm,user);

        user.setPassword(EncryptUtils.encrypt(userForm.getPassword()));

        return user;
    }
}
