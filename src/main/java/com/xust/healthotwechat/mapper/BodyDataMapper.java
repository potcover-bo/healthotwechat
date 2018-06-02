package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.BodyData;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 身体健康数据mapper
 */
@Mapper
public interface BodyDataMapper {

    /**插入一条数据*/
    @Insert("insert into body_data(phone,weight,today_step_count,create_time) " +
            "values(#{phone},#{weight},#{todayStepCount},#{createTime})")
    int insert(BodyData bodyData);


    /**
     * 查询最近四条历史记录
     * @param phone
     * @return
     */
    @Select("select phone,weight,today_step_count,create_time from body_data where phone = #{phone} " +
            " order by create_time desc limit 7")
    @Results({
            @Result(property = "phone", column = "phone"),
            @Result(property = "weight", column = "weight"),
            @Result(property = "todayStepCount", column = "today_step_count"),
            @Result(property = "createTime", column = "create_time"),
    })
    List<BodyData> findBodyDataList(@Param("phone") String phone);
}
