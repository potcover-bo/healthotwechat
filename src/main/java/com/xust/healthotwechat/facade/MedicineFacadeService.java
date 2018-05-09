package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.convert.MedicineConvertService;
import com.xust.healthotwechat.entity.Medicine;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.form.MedicineForm;
import com.xust.healthotwechat.service.MedicineService;
import com.xust.healthotwechat.utils.DateUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 服药业务归集
 */
@Service
@Slf4j
public class MedicineFacadeService {

    @Autowired
    private MedicineService medicineService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private MedicineConvertService medicineConvertService;


    /**
     * 录入服药数据
     * @param medicineForm
     * @return
     */
    public int entry(MedicineForm medicineForm){

        try {

            String key = medicineForm.getOpenid()+"_medicine";

            /**用户当天已经录入*/
            if (redisTemplate.opsForValue().get(key) != null){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getCode(),
                            HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getMessage());
            }

            /**数据进行转换*/
            Medicine medicine = medicineConvertService.formToEntity(medicineForm);

            /**redis中加入标记表示用户当天已经录入*/
            redisTemplate.opsForValue().set(key,DateUtils.nowDateFormat(),DateUtils.getRemainSeconds(),TimeUnit.SECONDS);

            /**数据进行录入*/
            return medicineService.insert(medicine);
        }catch (Exception e){
            log.error("录入服药数据异常={}",e.getMessage());
        }
        return -1;
    }
}
