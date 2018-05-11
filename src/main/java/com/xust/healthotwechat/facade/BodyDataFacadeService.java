package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.convert.BodyDataConvertService;
import com.xust.healthotwechat.dto.BodyDataDto;
import com.xust.healthotwechat.entity.BodyData;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.form.BodyDataForm;
import com.xust.healthotwechat.service.BodyDataService;
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
 * 身体数据业务归集
 *
 */
@Service
@Slf4j
public class BodyDataFacadeService {

    @Autowired
    private BodyDataService bodyDataService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private BodyDataConvertService bodyDataConvertService;

    @Transactional
    public int entry(BodyDataForm bodyDataForm){

      /**检查用户当天是否已经录入数据*/

      try {
          String key = bodyDataForm.getPhone()+"bodyData";

          /**数据转换*/
          BodyData bodyData = bodyDataConvertService.formToEntity(bodyDataForm);

          /**redis加入标记表示当天已经录入*/
          if (redisTemplate.opsForValue().get(key) !=null ){
              throw new HealthOTWechatException(HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getCode(),
                      HealthOTWechatErrorCode.ALREADY_ENTRY_DATA.getMessage());
          }


          redisTemplate.opsForValue().set(key,DateUtils.nowDateFormat(),DateUtils.getRemainSeconds(),TimeUnit.SECONDS);

          /**录入数据*/
         return bodyDataService.insert(bodyData);
      }catch (Exception e){
          log.error("录入身体数据异常={}",e.getMessage());
          throw e;

      }

    }


    /**
     * 查询身体数据历史记录
     * @param phone
     * @return
     */
    public List<BodyDataDto>  findBodyDataListByOpenid(@Param("phone") String phone){

        List<BodyData> bodyDataList = bodyDataService.findBodyDataList(phone);

        List<BodyDataDto> bodyDataDtoList = new ArrayList<>();
        try {
            if(bodyDataList == null || bodyDataList.size() == 0){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.DATA_NO_EXIST.getCode(),
                        HealthOTWechatErrorCode.DATA_NO_EXIST.getMessage());
            }

            /**数据转换*/
            bodyDataDtoList = bodyDataConvertService.entityToDto(bodyDataList);

        }catch (Exception e){
            log.error("查询身体数据历史记录={}",phone+":"+e.getMessage());
            throw e;
        }

        return bodyDataDtoList;
    }
}
