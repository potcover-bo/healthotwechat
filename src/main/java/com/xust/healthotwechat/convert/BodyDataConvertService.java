package com.xust.healthotwechat.convert;

import com.xust.healthotwechat.dto.BodyDataDto;
import com.xust.healthotwechat.entity.BodyData;
import com.xust.healthotwechat.form.BodyDataForm;
import com.xust.healthotwechat.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 身体指数数据转换
 */
@Service
public class BodyDataConvertService {


    /**
     *  将身体数据form 转换成实体类对象
     * @param bodyDataForm
     * @return
     */
    public  BodyData formToEntity(BodyDataForm bodyDataForm){

        BodyData bodyData = new BodyData();

        BeanUtils.copyProperties(bodyDataForm,bodyData);

        bodyData.setCreateTime(new Date());

        return bodyData;
    }


    /**
     * 身体数据实体转换为曲线对象
     * @param bodyDataList
     * @return
     */
    public List<BodyDataDto> entityToDto(List<BodyData> bodyDataList){

        List<BodyDataDto> bodyDataDtoList = new ArrayList<>(bodyDataList.size());

        for(BodyData bodyData : bodyDataList){
            BodyDataDto bodyDataDto =  new BodyDataDto();

            bodyDataDto.setWeight(bodyData.getWeight());
            bodyDataDto.setTodayStepCount(bodyData.getTodayStepCount());

            bodyDataDto.setCreateTime(DateUtils.dateToString(bodyData.getCreateTime()));

            bodyDataDtoList.add(bodyDataDto);
        }

        return bodyDataDtoList;

    }
}
