package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.AjaxResultVo;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.SleepingDto;
import com.xust.healthotwechat.facade.SleepingFacadeService;
import com.xust.healthotwechat.form.SleepingForm;
import com.xust.healthotwechat.utils.AjaxResultVOUtils;
import com.xust.healthotwechat.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 睡眠controller
 */
@RestController
@Slf4j
@RequestMapping("/sleeping")
public class SleepingController {

    @Autowired
    private SleepingFacadeService sleepingFacadeService;

    @PostMapping("/entry")
    public String entry(@Valid SleepingForm sleepingForm,
                        BindingResult bindingResult){

        Gson gson = new Gson();

        AjaxResultVo resultVo;

        if (bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        try {

            sleepingFacadeService.entry(sleepingForm);
            resultVo = AjaxResultVOUtils.success();


        }catch (Exception e){
            log.error("睡眠数据录入异常={}",sleepingForm.getPhone()+e.getMessage());
            resultVo = AjaxResultVOUtils.error(e.getMessage());

        }
        return gson.toJson(resultVo);
    }

    /**
     * 查询睡眠历史记录
     * @param phone 手机号码
     * @return resultvo对象
     */
    @GetMapping("/history")
    public ResultVO<List<SleepingDto>> history(@RequestParam("phone")String phone){

        List<SleepingDto> historyList;


        try {
            historyList = sleepingFacadeService.findSleepingListByOpenid(phone);

        }catch (Exception e){
            log.error("查询睡眠情况={}",e.getMessage());
            return ResultVOUtils.error(60005,e.getMessage());
        }


        return ResultVOUtils.success(historyList);
    }
}
