package com.xust.healthotwechat.service;

import com.xust.healthotwechat.entity.BodyData;
import com.xust.healthotwechat.mapper.BodyDataMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 身体数据service
 */
@Service
public class BodyDataService {

    @Autowired
    private BodyDataMapper bodyDataMapper;


    /**
     * 插入一条记录
     * @param bodyData
     * @return
     */
    public int insert(BodyData bodyData){
        return bodyDataMapper.insert(bodyData);
    }


    /**
     * 根据phone查询最近十条历史记录
     * @param phone
     * @return
     */
    public List<BodyData> findBodyDataList(@Param("phone") String phone){
        return bodyDataMapper.findBodyDataList(phone);
    }
}
