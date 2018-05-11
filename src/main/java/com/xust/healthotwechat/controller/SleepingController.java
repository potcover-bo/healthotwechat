package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.SleepingDto;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.SleepingFacadeService;
import com.xust.healthotwechat.form.SleepingForm;
import com.xust.healthotwechat.utils.ResultVOUtils;
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
 * Created by evildoerdb_ on 2018/5/9
 *
 * 睡眠controller
 */
@Controller
@Slf4j
@RequestMapping("/sleeping")
public class SleepingController {

    @Autowired
    private SleepingFacadeService sleepingFacadeService;

    @PostMapping("/entry")
    public ModelAndView entry(@Valid SleepingForm sleepingForm,
                        BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }

        try {

            /**进行录入*/
            int result =  sleepingFacadeService.entry(sleepingForm);

            if (-1 == result){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.SLEEPING_DATA_ENTRY_ERROE.getCode(),
                        HealthOTWechatErrorCode.SLEEPING_DATA_ENTRY_ERROE.getMessage());
            }

        }catch (Exception e){
            log.error("睡眠数据录入异常={}",e.getMessage());
            return new ModelAndView("redirect:/error.html");

        }
        return new ModelAndView("redirect:/index.html");
    }

    /**
     * 查询睡眠历史记录
     * @param phone
     * @return
     */
    @RequestMapping("/history")
    @ResponseBody
    public ResultVO<List<SleepingDto>> history(@RequestParam("phone")String phone){

        List<SleepingDto> historyList;


        try {
            historyList = sleepingFacadeService.findSleepingListByOpenid(phone);

        }catch (Exception e){
            log.error("查询睡眠情况={}",e.getMessage());
            return ResultVOUtils.error("60005",e.getMessage());
        }


        return ResultVOUtils.success(historyList);
    }
}
