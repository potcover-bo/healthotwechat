package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BodyDataDto;
import com.xust.healthotwechat.facade.BodyDataFacadeService;
import com.xust.healthotwechat.form.BodyDataForm;
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
 * 身体指数controller
 */
@Controller
@RequestMapping("/bodydata")
@Slf4j
public class BodyDataController {


    @Autowired
    private BodyDataFacadeService bodyDataFacadeService;

    @PostMapping("/entry")
    public ModelAndView  entry(@Valid BodyDataForm bodyDataForm,
                               BindingResult bindingResult,Map<String,Object> map){

        /**如果参数校验出错*/
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        try {

            bodyDataFacadeService.entry(bodyDataForm);
            map.put("message","成功了");
            map.put("url","index.html");

        }catch (Exception e){
            log.error("身体数据录入异常={}",bodyDataForm.getPhone()+e.getMessage());
            map.put("message",e.getMessage());
            map.put("url","index.html");
            return new ModelAndView("common/error",map);

        }

        return new ModelAndView("common/success",map);
    }


    /**
     * 根据openid查询历史记录   绘制图标
     * @param phone
     * @return
     */
    @GetMapping("/history")
    @ResponseBody
    public ResultVO<List<BodyDataDto>> history(@RequestParam("phone")String phone){


        List<BodyDataDto> historyList;
        try {

            /**查询历史记录*/
            historyList = bodyDataFacadeService.findBodyDataListByOpenid(phone);

        }catch (Exception e){
            log.error("【查询身体数据异常】={}",e.getMessage());

            return ResultVOUtils.error("60003",e.getMessage());
        }

        return ResultVOUtils.success(historyList);
    }
}
