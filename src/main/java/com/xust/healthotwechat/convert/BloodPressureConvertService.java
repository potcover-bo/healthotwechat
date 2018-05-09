package com.xust.healthotwechat.convert;

import com.xust.healthotwechat.dto.BloodPressureDto;
import com.xust.healthotwechat.entity.BloodPressure;
import com.xust.healthotwechat.form.BloodPressureForm;
import com.xust.healthotwechat.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血压数据转换
 */
@Service
public class BloodPressureConvertService {


    /**
     * 血压form表单转换成entity
     * @return
     */
    public  BloodPressure formToEntity(BloodPressureForm bloodPressureForm){
        BloodPressure bloodPressure = new BloodPressure();

        BeanUtils.copyProperties(bloodPressureForm,bloodPressure);


        bloodPressure.setMeasureTime(new Date());


        return  bloodPressure;
    }


    /**
     * 将实体类转换成图像显示需要用的数据
     * @param bloodPressureList
     * @return
     */
    public  List<BloodPressureDto> entityToDto(List<BloodPressure> bloodPressureList){

        List<BloodPressureDto> bloodPressureDtoList = new ArrayList<>(bloodPressureList.size());


        for(BloodPressure bloodPressure : bloodPressureList){
            BloodPressureDto bloodPressureDto = new BloodPressureDto();

            bloodPressureDto.setHighPressure(bloodPressure.getHighPressure());
            bloodPressureDto.setLowPressure(bloodPressure.getLowPressure());
            bloodPressureDto.setMeasureTime(DateUtils.dateToString(bloodPressure.getMeasureTime()));

            bloodPressureDtoList.add(bloodPressureDto);

        }
        return  bloodPressureDtoList;
    }
}
