package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.MoodDto;
import com.xust.healthotwechat.facade.MoodFacadeService;
import com.xust.healthotwechat.form.MoodForm;
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
                              BindingResult bindingResult,Map<String,Object> map){


        if(bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        try {

            moodFacadeService.entry(moodForm);
            map.put("message","成功了");
            map.put("url","index.html");

        }catch (Exception e){
            /**数据录入异常*/
            log.error("录入心情数据异常={}",moodForm.getPhone()+e.getMessage());
            map.put("message",e.getMessage());
            map.put("url","index.html");
            return new ModelAndView("common/error",map);
        }
        return new ModelAndView("common/success",map);

    }

    /**
     * 查询心情历史记录
     * @param phone
     * @return
     */
    @GetMapping("/history")
    @ResponseBody
    public ResultVO<List<MoodDto>> history(@RequestParam("phone")String phone){

        List<MoodDto> historyList;


        try {
            historyList = moodFacadeService.findMoodListByOpenid(phone);


        }catch (Exception e){
            log.error("查询心情历史记录={}",e.getMessage());
            return ResultVOUtils.error("60004",e.getMessage());
        }

        return ResultVOUtils.success(historyList);
    }
}
