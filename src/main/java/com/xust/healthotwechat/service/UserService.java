package com.xust.healthotwechat.service;

import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by evildoerdb_ on 2018/5/6
 *
 * 用户service
 */

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 插入一条数据
     * @param user
     * @return
     */
    int insert(User user){
        return userMapper.insert(user);
    }


}
