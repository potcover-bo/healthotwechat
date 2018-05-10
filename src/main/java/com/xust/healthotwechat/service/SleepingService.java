package com.xust.healthotwechat.service;

import com.xust.healthotwechat.entity.Sleeping;
import com.xust.healthotwechat.mapper.SleepingMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 睡眠service
 */
@Service
public class SleepingService {

    @Autowired
    private SleepingMapper sleepingMapper;

    /**
     * 插入一条记录
     * @param sleeping
     * @return
     */
    public  int insert(Sleeping sleeping){
        return sleepingMapper.insert(sleeping);
    }


    /**
     * 根据phone查询历史记录
     * @param phone
     * @return
     */
    public List<Sleeping> findSleepingList(@Param("phone")String phone){
        return sleepingMapper.findSleepingList(phone);
    }
}
