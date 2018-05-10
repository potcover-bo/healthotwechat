package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.dto.BloodSugarDto;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BloodSugarFacadeService;
import com.xust.healthotwechat.form.BloodSugarForm;
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


    @RequestMapping("history")
    @ResponseBody
    public String getData(@RequestParam("phone") String phone){
        Gson gson = new Gson();


        Map<String,Object> resultMap = new HashMap<>();

        List<BloodSugarDto> historyList = new ArrayList<>();
        try {

            historyList = bloodSugarFacadeService.findBloodSugarListByOpenid(phone);
            resultMap.put("data",historyList);
        }catch (HealthOTWechatException e){
            log.error("查询历史记录={}",e.getMessage());
        }
        catch (Exception e){
            log.info("查询数据出错");
        }

        return gson.toJson(resultMap);
    }
}
