package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.dto.MoodDto;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.MoodFacadeService;
import com.xust.healthotwechat.form.MoodForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 心情controller
 */

@Controller
@Slf4j
@RequestMapping("/mood")
public class MoodController {

    @Autowired
    private MoodFacadeService moodFacadeService;


    /**
     * 录入心情数据
     * @param moodForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/entry")
    public ModelAndView entry(@Valid MoodForm moodForm,
                              BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();

        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }

        try {

            /**录入数据*/
            int result = moodFacadeService.entry(moodForm);

            if (result == -1 ){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.MOOD_DATA_ENTRY_ERROE.getCode(),
                        HealthOTWechatErrorCode.MOOD_DATA_ENTRY_ERROE.getMessage());
            }

            modelAndView.setViewName("redirect: /index.html");

        }catch (Exception e){
            /**数据录入异常*/
            log.error("录入心情数据异常={}",e.getMessage());
            modelAndView.setViewName("redirect: /error.html");
        }
        return  modelAndView;

    }

    /**
     * 查询心情历史记录
     * @param openid
     * @return
     */
    @RequestMapping("/history")
    @ResponseBody
    public String history(@RequestParam("openid")String openid){
        Map<String,Object> resultMap = new HashMap<>();

        List<MoodDto> historyList = new ArrayList<>();

        Gson gson = new Gson();

        try {
            historyList = moodFacadeService.findMoodListByOpenid(openid);

            if(historyList == null || historyList.size() == 0){
                resultMap.put("message","用户历史记录为空");
            }
            resultMap.put("data",historyList);

        }catch (Exception e){
            log.error("查询心情历史记录={}",e.getMessage());
            resultMap.put("message","对不起，查询出错 请重新录入或者继续进行查询");
        }

        return gson.toJson(resultMap);
    }
}
