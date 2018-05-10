package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.BloodSugar;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/4/27
 *
 * 血糖mapper
 */

@Mapper
public interface BloodSugarMapper {

    /**插入一条记录*/
    @Insert("insert into blood_sugar(phone,blood_sugar_value,meal_condition,medicine_condition," +
            "save_health_record,measure_time) " +
            "values(#{phone},#{bloodSugarValue},#{mealCondition},#{medicineCondition}," +
            "#{saveHealthRecord},#{measureTime})")
    int insert(BloodSugar bloodSugar);


    /**
     * 查询最近十条历史记录
     * @param phone
     * @return
     */
    @Select("select phone,blood_sugar_value,measure_time from blood_sugar where phone = #{phone} " +
            "order by measure_time desc limit 10")
    @Results({
            @Result(property = "phone", column = "phone"),
            @Result(property = "bloodSugarValue", column = "blood_sugar_value"),
            @Result(property = "measureTime", column = "measure_time"),
    })
    List<BloodSugar> findBloodSugarList(@Param("phone")String phone);
}
