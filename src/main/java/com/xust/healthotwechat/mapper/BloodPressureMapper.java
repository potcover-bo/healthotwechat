package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.BloodPressure;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/4/27
 *
 * 血压mapper
 */
@Mapper
public interface BloodPressureMapper {

    /**插入一条数据.*/
    @Insert("insert into blood_pressure(phone,high_pressure,low_pressure,meal_condition," +
            "medicine_condition,save_health_record,measure_time) " +
            "values(#{phone},#{highPressure},#{lowPressure},#{mealCondition}," +
            "#{medicineCondition},#{saveHealthRecord},#{measureTime})")
    int insert(BloodPressure bloodPressure);


    /**根据openid查询最近十条记录*/
    @Select("select phone,high_pressure,low_pressure,measure_time from blood_pressure where phone = #{phone} " +
            "order by measure_time asc limit 10")
    @Results({
            @Result(property = "phone", column = "phone"),
            @Result(property = "highPressure", column = "high_pressure"),
            @Result(property = "lowPressure", column = "low_pressure"),
            @Result(property = "measureTime", column = "measure_time"),
    })
    List<BloodPressure> findBloodPressureList(@Param("phone") String phone);
}
