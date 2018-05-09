package com.xust.healthotwechat.facade;

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
public class UserFacadeService {

    @Autowired
    private UserService userService;


    @Autowired
    private RedisTemplate redisTemplate;


    @Transactional
    public boolean login(String username,String password){

        return true;
    }


    /**
     * 生成验证码  并存放到redis中去
     * @param phone
     * @return
     */

    public String verificationCode(String phone){
        String code = SmsUtils.verificationCode();

        return  code;
    }
}
