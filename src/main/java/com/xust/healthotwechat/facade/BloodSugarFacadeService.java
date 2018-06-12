package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.convert.BloodSugarConvertService;
import com.xust.healthotwechat.dto.BloodSugarDto;
import com.xust.healthotwechat.entity.BloodSugar;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.form.BloodSugarForm;
import com.xust.healthotwechat.service.BloodSugarService;
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
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血糖业务归集
 *
 */
@Service
@Slf4j
public class BloodSugarFacadeService {

    @Autowired
    private BloodSugarService bloodSugarService;

    @Autowired
    private BloodSugarConvertService bloodSugarConvertService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 录入血糖数据
     *
     * @param bloodSugarForm
     * @return
     */

    @Transactional
    public int entryBloodSugar(BloodSugarForm bloodSugarForm){


        try {
            String key = bloodSugarForm.getPhone()+"_bloodSugar";

            /**用户当天已经录入过血压*/
//            if(redisTemplate.opsForValue().get(key) != null){
//                throw new HealthOTWechatException(HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getCode(),
//                        HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getMessage());
//            }

            /**数据转换*/
            BloodSugar bloodSugar = bloodSugarConvertService.formToEntity(bloodSugarForm);

            /**redis中加入标记表示当天已经录入过 key : openid+bloodSugar value: 录入时间 expire:当天剩余时间*/
            redisTemplate.opsForValue().set(key,DateUtils.nowDateFormat(),DateUtils.getRemainSeconds(),TimeUnit.SECONDS);

            /**redis中加入标记表示三天内录入过*/
            redisTemplate.opsForValue().set(bloodSugarForm.getPhone()+"_three_entry_bloodSugar",
                    "bloodSugar",DateUtils.getRemainSeconds()+172800,TimeUnit.SECONDS);

            /**插入数据*/
            return bloodSugarService.insert(bloodSugar);

        }catch (Exception e){
            log.error("录入血糖异常={}",e.getMessage());
            throw e;

        }

    }


    /**
     * 查询血糖历史记录
     *
     * @param phone
     * @return
     */
    public List<BloodSugarDto> findBloodSugarListByOpenid(String phone){
        List<BloodSugarDto> historyList;

        try {

            /**查询历史记录*/
            List<BloodSugar> bloodSugarList = bloodSugarService.findBloodSugarList(phone);

            if( bloodSugarList == null || bloodSugarList.size()==0){
                throw  new HealthOTWechatException(HealthOTWechatErrorCode.DATA_NO_EXIST.getCode(),
                        HealthOTWechatErrorCode.DATA_NO_EXIST.getMessage());
            }

            /**将数据进行转换*/
            historyList = bloodSugarConvertService.entityToDto(bloodSugarList);


        }catch (Exception e){
            log.error("血压历史记录查询={}",phone+":"+e.getMessage());
            throw e;
        }

        return historyList;
    }
}
