package com.xust.healthotwechat.mapper;

import com.xust.healthotwechat.entity.Medicine;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by evildoerdb_ on 2018/5/4
 *
 * 服药mapper
 */
@Mapper
public interface MedicineMapper {


    /**插入一条数据*/
    @Insert("insert into medicine(morning_medicine,morning_number,noon_medicine," +
            "noon_number,night_medicine,night_number,create_time) " +
            "values(#{morningMedicine},#{morningNumber},#{noonMedicine}," +
            "#{noonNumber},#{nightMedicine},#{nightNumber},#{createTime})")
    int insert(Medicine medicine);
}
