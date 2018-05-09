package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.CustodyUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 监护人mapper
 */
@Mapper
public interface CustodyUserMapper {

    /**插入一条记录.*/
    @Insert("insert into custody_user(openid,phone,idcard,username,password," +
            "headimgurl,sex,age,custody_openid,custody_relationship) " +
            "values(#{openid},#{phone},#{idcard},#{username},#{password}," +
            "#{headimgurl},#{sex},#{age},#{custodyOpenid},#{custodyRelationship})")
    int insert(CustodyUser custodyUser);
}
