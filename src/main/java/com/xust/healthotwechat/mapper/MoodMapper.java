package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.Mood;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 心情mapper
 */

@Mapper
public interface MoodMapper {

    /**插入一条数据.*/
    @Insert("insert into mood(phone,morning_mood,noon_mood,night_mood,create_time) " +
            "values(#{phone},#{morningMood},#{noonMood},#{nightMood},#{createTime})")
    int insert(Mood mood);


    /**查询历史记录*/
    @Select("select phone,morning_mood,noon_mood,night_mood,create_time from mood " +
            "where phone = #{phone} order by create_time asc limit 10")
    @Results({
            @Result(column = "phone",property = "phone"),
            @Result(column = "morning_mood",property = "morningMood"),
            @Result(column = "noon_mood",property = "noonMood"),
            @Result(column = "night_mood",property = "nightMood"),
            @Result(column = "create_time",property = "createTime"),
    })
    List<Mood> findMoodList(@Param("phone")String phone);
}
