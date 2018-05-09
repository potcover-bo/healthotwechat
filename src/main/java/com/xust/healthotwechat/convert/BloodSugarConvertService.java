package com.xust.healthotwechat.convert;

import com.xust.healthotwechat.dto.BloodSugarDto;
import com.xust.healthotwechat.entity.BloodSugar;
import com.xust.healthotwechat.form.BloodSugarForm;
import com.xust.healthotwechat.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血糖数据转换
 */
@Service
public class BloodSugarConvertService {


    /**
     * 血糖的form表单转换为entity
     * @param bloodSugarForm
     * @return
     */
    public  BloodSugar formToEntity(BloodSugarForm bloodSugarForm){
        BloodSugar bloodSugar = new BloodSugar();

        BeanUtils.copyProperties(bloodSugarForm,bloodSugar);

        bloodSugar.setMeasureTime(new Date());

        return bloodSugar;
    }

    /**
     * 血糖的list转换为前端需要的数据list
     * @param bloodSugarList
     * @return
     */
    public  List<BloodSugarDto> entityToDto(List<BloodSugar> bloodSugarList){

        List<BloodSugarDto> bloodSugarDtoList = new ArrayList<>(bloodSugarList.size());

        for (BloodSugar bloodSugar : bloodSugarList){
            BloodSugarDto bloodSugarDto = new BloodSugarDto();

            bloodSugarDto.setBloodSugarValue(bloodSugar.getBloodSugarValue());
            bloodSugarDto.setMeasureTime(DateUtils.dateToString(bloodSugar.getMeasureTime()));

            bloodSugarDtoList.add(bloodSugarDto);
        }

        return bloodSugarDtoList;
    }
}
