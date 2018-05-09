package com.xust.healthotwechat.convert;

import com.xust.healthotwechat.dto.SleepingDto;
import com.xust.healthotwechat.entity.Sleeping;
import com.xust.healthotwechat.form.SleepingForm;
import com.xust.healthotwechat.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 睡眠数据转换
 */
@Service
public class SleepingConvertService {


    /**
     * 睡眠form转换为entity
     * @param sleepingForm
     * @return
     */
    public Sleeping formToEntity(SleepingForm sleepingForm){
        Sleeping sleeping = new Sleeping();

        BeanUtils.copyProperties(sleepingForm,sleeping);

        sleeping.setCreateTime(new Date());

        return sleeping;
    }


    /**
     * 将实体类数据转换为前端需要的数据
     * @param sleepingList
     * @return
     */
    public List<SleepingDto> entityToDto(List<Sleeping> sleepingList){
        List<SleepingDto> sleepingDtoList = new ArrayList<>(sleepingList.size());

        for (Sleeping sleeping : sleepingList){
            SleepingDto sleepingDto = new SleepingDto();

            sleepingDto.setNoonTime(sleeping.getNoonTime());
            sleepingDto.setNightTime(sleeping.getNightTime());
            sleepingDto.setCreateTime(DateUtils.dateToString(sleeping.getCreateTime()));

            sleepingDtoList.add(sleepingDto);
        }

        return sleepingDtoList;
    }
}
