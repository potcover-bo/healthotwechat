package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.convert.UserConvertService;
import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.form.UserForm;
import com.xust.healthotwechat.service.UserService;
import com.xust.healthotwechat.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by evildoerdb_ on 2018/5/6
 *
 * 用户业务归集
 */

@Service
@Slf4j
public class  UserFacadeService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvertService userConvertService;

    @Autowired
    private RedisTemplate redisTemplate;


    public boolean login(String username,String password){

        return true;
    }



    @Transactional
    public int register(UserForm userForm){

        try {
            /**数据转换*/
            User user = userConvertService.formToEntity(userForm);

            /**数据库加入数据*/
            return userService.insert(user);

        }catch (Exception e){
            log.error("注册异常={}",e.getMessage());
        }

        return -1;
    }

    /**
     * 根据手机号码查询用户是否存在
     * @param phone
     * @return true 用户已经存在 false 用户不存在
     */
    public boolean userIsExist(String phone){

        User user = userService.findUserByPhone(phone);
        if(user !=null ){
            return true;
        }
        return false;
    }
}
