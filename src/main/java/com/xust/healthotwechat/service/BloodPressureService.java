package com.xust.healthotwechat.service;

import com.xust.healthotwechat.entity.BloodPressure;
import com.xust.healthotwechat.mapper.BloodPressureMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


/**
 * Created by evildoerdb_ on 2018/5/2
 *
 * 血压service
 */
@Service
public class BloodPressureService {


    @Autowired
    private BloodPressureMapper bloodPressureMapper;

    /**
     * 插入一条数据
     * @param bloodPressure
     * @return
     */
    public int intsert(BloodPressure bloodPressure){
        return bloodPressureMapper.insert(bloodPressure);
    }


    /**
     * 根据openid查询最近十条历史记录
     * @param phone
     * @return
     */
    public List<BloodPressure> findBloodPressureList(String phone){
        return bloodPressureMapper.findBloodPressureList(phone);
    }
}
