package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.convert.MoodConvertService;
import com.xust.healthotwechat.dto.MoodDto;
import com.xust.healthotwechat.entity.BloodPressure;
import com.xust.healthotwechat.entity.Mood;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.form.MoodForm;
import com.xust.healthotwechat.service.MoodService;
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
 * 心情业务归集
 */
@Service
@Slf4j
public class MoodFacadeService {

    @Autowired
    private MoodService moodService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MoodConvertService moodConvertService;


    /**
     * 插入心情数据
     * @param moodForm
     * @return
     */
    @Transactional
    public int entry(MoodForm moodForm){

        String key = moodForm.getPhone()+"mood";
        try {
            /**用户当天已经录入*/
            if (redisTemplate.opsForValue().get(key) != null){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getCode(),
                            HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getMessage());
            }

            /**数据转换*/
            Mood mood  = moodConvertService.formToEntity(moodForm);

            /**redis加入标记表示当天已经录入数据*/
            redisTemplate.opsForValue().set(key, DateUtils.nowDateFormat(),DateUtils.getRemainSeconds(),TimeUnit.SECONDS);

            /**redis中加入标记表示三天内录入过*/
            redisTemplate.opsForValue().set(moodForm.getPhone()+"_three_entry_mood",
                    "mood",DateUtils.getRemainSeconds()+172800,TimeUnit.SECONDS);

            /**录入数据*/
            return moodService.insert(mood);
        }catch (Exception e){
            log.error("录入心情数据异常={}",e.getMessage());
            throw e;
        }
    }


    /**
     * 心情历史记录查询
     * @param phone
     * @return
     */
    public /*List<MoodDto>*/MoodDto findMoodListByOpenid(@Param("phone")String phone){
        //List<MoodDto> historyList;
        MoodDto moodDto;

        try {

            /**查询历史记录*/
            List<Mood> moodList = moodService.findMoodList(phone);

            if(moodList==null || moodList.size()==0){
                throw  new HealthOTWechatException(HealthOTWechatErrorCode.DATA_NO_EXIST.getCode(),
                        HealthOTWechatErrorCode.DATA_NO_EXIST.getMessage());
            }

            /**将数据进行转换*/
            moodDto = moodConvertService.entityToDto(moodList);


        }catch (Exception e){
            log.error("心情历史记录查询={}",phone+":"+e.getMessage());
            throw e;
        }

        return moodDto;
    }
}
