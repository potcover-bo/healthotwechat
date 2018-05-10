package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.dto.BodyDataDto;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BodyDataFacadeService;
import com.xust.healthotwechat.form.BodyDataForm;
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
                               BindingResult bindingResult){

        /**如果参数校验出错*/
        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }

        try {
            int result =  bodyDataFacadeService.entry(bodyDataForm);

            if (-1 == result){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.BODY_DATA_ENTRY_ERROE.getCode(),
                        HealthOTWechatErrorCode.BODY_DATA_ENTRY_ERROE.getMessage());
            }

        }catch (Exception e){
            log.error("身体数据录入异常={}",e.getMessage());
            return new ModelAndView("redirect:/error.html");

        }



        return new ModelAndView("redirect:/index.html");
    }


    /**
     * 根据openid查询历史记录   绘制图标
     * @param phone
     * @return
     */
    @RequestMapping("/history")
    @ResponseBody
    public String history(@RequestParam("phone")String phone){

        Gson gson = new Gson();
        Map<String,Object> resultMap = new HashMap<>();

        List<BodyDataDto> historyList = new ArrayList<>();
        try {

            /**查询历史记录*/
            historyList = bodyDataFacadeService.findBodyDataListByOpenid(phone);

        }catch (Exception e){
            log.error(e.getMessage());
        }
        resultMap.put("data",historyList);

        return gson.toJson(resultMap);
    }
}
