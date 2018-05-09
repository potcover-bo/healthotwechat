package com.xust.healthotwechat.facade;

import com.xust.healthotwechat.service.CustodyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 监护人业务归集
 *
 */
@Service
public class CustodyUserFacadeService {

    @Autowired
    private CustodyUserService custodyUserService;
}
