package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.AjaxResultVo;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BloodSugarDto;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BloodSugarFacadeService;
import com.xust.healthotwechat.form.BloodSugarForm;
import com.xust.healthotwechat.utils.AjaxResultVOUtils;
import com.xust.healthotwechat.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    public String entry(@Valid BloodSugarForm bloodSugarForm,
                        BindingResult bindingResult){

        Gson gson = new Gson();

        AjaxResultVo resultVo;

        /**参数校验出错*/
        if(bindingResult.hasErrors()){
            throw  new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        try {
            bloodSugarFacadeService.entryBloodSugar(bloodSugarForm);
            resultVo = AjaxResultVOUtils.success();


        }catch (HealthOTWechatException e){
            log.error("录入血糖={}",bloodSugarForm.getPhone()+e.getMessage());
            resultVo = AjaxResultVOUtils.error(e.getMessage());
        }

        return gson.toJson(resultVo);
    }


    /**
     * 查询血糖历史记录
     * @param phone
     * @return
     */
    @GetMapping("/history")
    public ResultVO<List<BloodSugarDto>> getData(@RequestParam("phone") String phone){



        List<BloodSugarDto> historyList ;
        try {

            historyList = bloodSugarFacadeService.findBloodSugarListByOpenid(phone);



        } catch (Exception e){

            log.error("【查询历史记录异常】={}",e.getMessage());

            return ResultVOUtils.error(60002,e.getMessage());
        }
        return ResultVOUtils.success(historyList);
    }
}
