package com.xust.healthotwechat.service;

import com.xust.healthotwechat.entity.Medicine;
import com.xust.healthotwechat.mapper.MedicineMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 服药service
 */
@Service
public class MedicineService {

    @Autowired
    private MedicineMapper medicineMapper;

    /**
     * 插入一条记录
     * @param medicine
     * @return
     */
    public int insert(Medicine medicine){
        return medicineMapper.insert(medicine);
    }
}
