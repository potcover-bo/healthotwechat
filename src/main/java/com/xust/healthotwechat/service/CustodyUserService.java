package com.xust.healthotwechat.service;

import com.xust.healthotwechat.entity.CustodyUser;
import com.xust.healthotwechat.mapper.CustodyUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by evildoerdb_ on 2018/5/6
 *
 * 监护人service
 */
@Service
public class CustodyUserService {

    @Autowired
    private CustodyUserMapper custodyUserMapper;

    /**
     * 向监护人表中增加一条记录
     * @param custodyUser
     * @return
     */
    public  int insert(CustodyUser custodyUser){
        return custodyUserMapper.insert(custodyUser);
    }
}
