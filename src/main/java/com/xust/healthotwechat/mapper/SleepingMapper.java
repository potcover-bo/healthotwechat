package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.Sleeping;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 睡眠mapper
 */

@Mapper
public interface SleepingMapper {

    /**插入一条记录*/
    @Insert("insert into sleeping(phone,noon_time,night_time,create_time) " +
            "values(#{phone},#{noonTime},#{nightTime},#{createTime})")
    int insert(Sleeping sleeping);



    /**根据openid查询历史记录*/
    @Select("select phone,noon_time,night_time,create_time from sleeping where phone = #{phone} " +
            "order by create_time desc limit 7")
    @Results({
            @Result(property = "phone", column = "phone"),
            @Result(property = "noonTime", column = "noon_time"),
            @Result(property = "nightTime", column = "night_time"),
            @Result(property = "createTime", column = "create_time"),
    })
    List<Sleeping> findSleepingList(@Param("phone")String phone);

}
