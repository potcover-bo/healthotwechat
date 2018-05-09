package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.dto.BloodPressureDto;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BloodPressureFacadeService;
import com.xust.healthotwechat.form.BloodPressureForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
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
 * 血压controller
 */
@Controller
@RequestMapping("/bloodpressure")
@Slf4j
public class BloodPressureController {


    @Autowired
    private BloodPressureFacadeService bloodPressureFacadeService;


    /**
     * 录入血压
     *
     * @param bloodPressureForm
     * @param bindingResult
     */
    @PostMapping("/entry")
    public ModelAndView entryBloodPressure(@Valid BloodPressureForm bloodPressureForm,
                                   BindingResult bindingResult){

        /**参数校验出错*/
        if (bindingResult.hasErrors()){
            throw  new HealthOTWechatException(HealthOTWechatErrorCode.BLOOD_PRESSURE_ENTRY_ERROE.getCode(),
                                            HealthOTWechatErrorCode.BLOOD_PRESSURE_ENTRY_ERROE.getMessage());
        }


        try {
            bloodPressureFacadeService.entryBloodPressure(bloodPressureForm);


        }catch (HealthOTWechatException e){
            log.error("录入血压={}",e.getMessage());
        }

        return new ModelAndView("redirect:/index.html");
    }


    /**
     * 查询血压的历史记录  用于绘制曲线
     * @param openid
     * @return
     */
    @ResponseBody
    @RequestMapping("/history")
    public String getData(@RequestParam("openid") String openid){

        Gson gson = new Gson();

        Map<String,Object> resultMap = new HashMap<>();

        List<BloodPressureDto> historyList = new ArrayList<>();
        try {

            historyList = bloodPressureFacadeService.findBloodPressureList(openid);
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
