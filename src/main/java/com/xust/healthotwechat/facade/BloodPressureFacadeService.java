package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.convert.BloodPressureConvertService;
import com.xust.healthotwechat.dto.BloodPressureDto;
import com.xust.healthotwechat.entity.BloodPressure;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.form.BloodPressureForm;
import com.xust.healthotwechat.service.BloodPressureService;
import com.xust.healthotwechat.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by evildoerdb_ on 2018/5/2
 *
 *
 * 血压业务归集
 */

@Service
@Slf4j
public class BloodPressureFacadeService {

    @Autowired
    private BloodPressureService bloodPressureService;

    @Autowired
    private BloodPressureConvertService bloodPressureConvertService;

    @Autowired
    private RedisTemplate redisTemplate;


    /**
     * 录入数据
     * @param bloodPressureForm
     * @return
     */
    @Transactional
    public int entryBloodPressure(BloodPressureForm bloodPressureForm){


        try {

            String key = bloodPressureForm.getPhone()+"bloodPressure";

            /**用户当天已经录入过血压*/
            if(redisTemplate.opsForValue().get(key)!=null){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getCode(),
                                        HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getMessage());
            }

            /**数据转换*/
            BloodPressure bloodPressure = bloodPressureConvertService.formToEntity(bloodPressureForm);

            /**redis中加入标记表示当天已经录入过*/
            redisTemplate.opsForValue().set(key,DateUtils.nowDateFormat(),DateUtils.getRemainSeconds(),TimeUnit.SECONDS);

            /**插入数据*/
            return bloodPressureService.intsert(bloodPressure);

        }catch (Exception e){

            log.error("录入血压异常={}",e.getMessage());
            throw e;
        }

    }


    /**
     * 查询血压历史记录  用于绘制曲线
     *
     * @param phone
     * @return
     */
    public List<BloodPressureDto> findBloodPressureList(String phone){

        List<BloodPressureDto> historyList = new ArrayList<>();

        try {

            /**查询历史记录*/
            List<BloodPressure> bloodPressureList = bloodPressureService.findBloodPressureList(phone);

            if( bloodPressureList==null || bloodPressureList.size()==0){
                throw  new HealthOTWechatException(HealthOTWechatErrorCode.DATA_NO_EXIST.getCode(),
                                            HealthOTWechatErrorCode.DATA_NO_EXIST.getMessage());
            }

            /**将数据进行转换*/
            historyList = bloodPressureConvertService.entityToDto(bloodPressureList);


        }catch (Exception e){
            log.error("血压历史记录查询={}",phone+":"+e.getMessage());
            throw e;
        }

        return historyList;
    }


}
