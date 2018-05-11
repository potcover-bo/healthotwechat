package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BloodPressureDto;
import com.xust.healthotwechat.facade.BloodPressureFacadeService;
import com.xust.healthotwechat.form.BloodPressureForm;
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
                                           BindingResult bindingResult,
                                           Map<String,Object> map){

        /**参数校验出错*/
        if (bindingResult.hasErrors()){
            log.error("【录入血压】参数不正确 bloodPressureForm={}",bloodPressureForm);
            throw  new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }


        try {

            bloodPressureFacadeService.entryBloodPressure(bloodPressureForm);
            map.put("url","index.html");
            map.put("message","录入成功");


        }catch (Exception e){

            log.error("录入血压={}",bloodPressureForm.getPhone()+e.getMessage());
            map.put("url","index.html");
            map.put("message",e.getMessage());

            return new ModelAndView("common/error",map);
        }

        return new ModelAndView("common/success",map);
    }


    /**
     * 查询血压的历史记录  用于绘制曲线
     * @param phone
     * @return
     */
    @ResponseBody
    @GetMapping("/history")
    public ResultVO<List<BloodPressureDto>> getData(@RequestParam("phone") String phone){




        List<BloodPressureDto> historyList;
        try {

            historyList = bloodPressureFacadeService.findBloodPressureList(phone);


        } catch (Exception e){

            log.error("【查询历史记录异常】={}",e.getMessage());
            return ResultVOUtils.error("60001",e.getMessage());
        }

        return ResultVOUtils.success(historyList);

    }
}
