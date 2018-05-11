package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.convert.SleepingConvertService;
import com.xust.healthotwechat.dto.SleepingDto;
import com.xust.healthotwechat.entity.Sleeping;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.form.SleepingForm;
import com.xust.healthotwechat.service.SleepingService;
import com.xust.healthotwechat.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
 * 睡眠业务归集
 */
@Service
@Slf4j
public class SleepingFacadeService {

    @Autowired
    private SleepingService sleepingService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SleepingConvertService sleepingConvertService;

    /**
     * 录入睡眠数据
     * @param sleepingForm
     * @return
     */
    @Transactional
    public int entry(SleepingForm sleepingForm){

        try{

            String key = sleepingForm.getPhone()+"sleeping";

            /**用户当天已经录入*/
            if (redisTemplate.opsForValue().get(key) != null){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getCode(),
                        HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getMessage());
            }

            /**数据转换*/
            Sleeping sleeping = sleepingConvertService.formToEntity(sleepingForm);

            /**redis加入标记表示当天已经录入过*/
            redisTemplate.opsForValue().set(key,DateUtils.nowDateFormat(),DateUtils.getRemainSeconds(),TimeUnit.SECONDS);

            /**录入数据*/
            return sleepingService.insert(sleeping);

        }catch (Exception e){
            log.error("录入睡眠数据={}",e.getMessage());
            throw e;
        }
    }

    /**
     * 查询历史记录
     * @param phone
     * @return
     */
    public List<SleepingDto> findSleepingListByOpenid(@Param("phone")String phone){

        List<SleepingDto> histotyList;

        try {
            /**查询历史记录*/
            List<Sleeping> sleepingList = sleepingService.findSleepingList(phone);

            /**判断是否有历史记录*/
            if (sleepingList == null || sleepingList.size() == 0){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.DATA_NO_EXIST.getCode(),
                        HealthOTWechatErrorCode.DATA_NO_EXIST.getMessage());
            }

            /**数据进行转换*/
            histotyList = sleepingConvertService.entityToDto(sleepingList);

        }catch (Exception e){
            log.error("查询睡眠历史数据={}",phone+":"+e.getMessage());
            throw e;
        }
        return histotyList;
    }
}









