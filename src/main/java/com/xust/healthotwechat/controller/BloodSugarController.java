package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BloodSugarDto;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BloodSugarFacadeService;
import com.xust.healthotwechat.form.BloodSugarForm;
import com.xust.healthotwechat.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血糖controller
 */
@Controller
@RequestMapping("/bloodsugar")
@Slf4j
public class BloodSugarController {

    @Autowired
    private BloodSugarFacadeService bloodSugarFacadeService;

    @RequestMapping("/entry")
    public ModelAndView entry(@Valid BloodSugarForm bloodSugarForm,
                        BindingResult bindingResult){

        /**参数校验出错*/
        if(bindingResult.hasErrors()){
            throw  new RuntimeException(bindingResult.getAllErrors().toString());
        }

        try {
            bloodSugarFacadeService.entryBloodSugar(bloodSugarForm);


        }catch (HealthOTWechatException e){
            log.error("录入血糖={}",e.getMessage());
        }

        return new ModelAndView("redirect:/index.html");
    }


    /**
     * 查询血糖历史记录
     * @param phone
     * @return
     */
    @RequestMapping("/history")
    @ResponseBody
    public ResultVO<List<BloodSugarDto>> getData(@RequestParam("phone") String phone){



        List<BloodSugarDto> historyList ;
        try {

            historyList = bloodSugarFacadeService.findBloodSugarListByOpenid(phone);



        } catch (Exception e){

            log.error("【查询历史记录异常】={}",e.getMessage());

            return ResultVOUtils.error("60002",e.getMessage());
        }
        return ResultVOUtils.success(historyList);
    }
}
