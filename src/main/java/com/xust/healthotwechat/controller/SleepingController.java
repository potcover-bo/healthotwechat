package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.SleepingDto;
import com.xust.healthotwechat.facade.SleepingFacadeService;
import com.xust.healthotwechat.form.SleepingForm;
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
                        BindingResult bindingResult,Map<String,Object> map){
        if (bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        try {

            sleepingFacadeService.entry(sleepingForm);
            map.put("message","成功了");
            map.put("url","index.html");


        }catch (Exception e){
            log.error("睡眠数据录入异常={}",sleepingForm.getPhone()+e.getMessage());
            map.put("message",e.getMessage());
            map.put("url","index.html");
            return new ModelAndView("common/error",map);

        }
        return new ModelAndView("common/success",map);
    }

    /**
     * 查询睡眠历史记录
     * @param phone 手机号码
     * @return resultvo对象
     */
    @GetMapping("/history")
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
