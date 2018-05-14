package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

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

    @Select("select phone,idcard,headimgurl,username,password,sex,age,custody_phone from user where phone = #{phone}")
    @Results({
            @Result(column = "phone",property = "phone"),
            @Result(column = "idcard",property = "idcard"),
            @Result(column = "headimgurl",property = "headimgurl"),
            @Result(column = "username",property = "username"),
            @Result(column = "password",property = "password"),
            @Result(column = "sex",property = "sex"),
            @Result(column = "age",property = "age"),
            @Result(column = "custody_phone",property = "custodyPhone"),
    })
    User finfUserByPhone(String phone);


    /**
     * 修改密码
     * @param user
     * @return
     */
    @Update("UPDATE user SET password = #{password} WHERE phone = #{phone}")
    int updatePassword(User user);


    /**
     * 查询所有用户的手机号码
     * @return
     */
    @Select("select phone from user")
    List<String> findAllPhone();
}
