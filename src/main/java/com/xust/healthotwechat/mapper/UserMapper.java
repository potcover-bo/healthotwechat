package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * Created by evildoerdb_ on 2018/4/27
 *
 * 用户mapper
 */
@Mapper
public interface UserMapper {



    /**插入一条记录*/
    @Insert("insert into user(phone,idcard,headimgurl,username,password," +
            "sex,age,custody_phone) " +
            "values(#{phone},#{idcard},#{headimgurl},#{username},#{password}," +
            "#{sex},#{age},#{custodyPhone})")
    int insert(User user);



}
