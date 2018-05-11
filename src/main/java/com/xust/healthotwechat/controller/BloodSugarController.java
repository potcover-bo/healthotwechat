package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BloodSugarDto;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BloodSugarFacadeService;
import com.xust.healthotwechat.form.BloodSugarForm;
import com.xust.healthotwechat.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
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

    @PostMapping("/entry")
    public ModelAndView entry(@Valid BloodSugarForm bloodSugarForm,
                        BindingResult bindingResult,
                              Map<String,Object> map){

        /**参数校验出错*/
        if(bindingResult.hasErrors()){
            throw  new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        try {
            bloodSugarFacadeService.entryBloodSugar(bloodSugarForm);
            map.put("message","成功了");
            map.put("url","index.html");


        }catch (HealthOTWechatException e){
            log.error("录入血糖={}",bloodSugarForm.getPhone()+e.getMessage());
            map.put("message",e.getMessage());
            map.put("url","index.html");
            return new ModelAndView("common/error",map);
        }

        return new ModelAndView("common/success",map);
    }


    /**
     * 查询血糖历史记录
     * @param phone
     * @return
     */
    @GetMapping("/history")
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
