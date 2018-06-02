package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.AjaxResultVo;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.MoodDto;
import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.MoodFacadeService;
import com.xust.healthotwechat.facade.UserFacadeService;
import com.xust.healthotwechat.form.MoodForm;
import com.xust.healthotwechat.utils.AjaxResultVOUtils;
import com.xust.healthotwechat.utils.EncryptUtils;
import com.xust.healthotwechat.utils.ResultVOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 心情controller
 */

@RestController
@Slf4j
@RequestMapping("/mood")
public class MoodController {

    @Autowired
    private MoodFacadeService moodFacadeService;

    @Autowired
    private UserFacadeService userFacadeService;


    /**
     * 录入心情数据
     * @param moodForm
     * @param bindingResult
     * @return
     */
    @PostMapping("/entry")
    public String entry(@Valid MoodForm moodForm,
                        BindingResult bindingResult,
                        HttpServletRequest request){

        Gson gson = new Gson();

        AjaxResultVo resultVo;


        try {


            String phone = (String) request.getSession().getAttribute("user");
            moodForm.setPhone(phone);

            if(bindingResult.hasErrors()){
                throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
            }

            moodFacadeService.entry(moodForm);
            resultVo = AjaxResultVOUtils.success("录入成功");

        }catch (Exception e){
            /**数据录入异常*/
            log.error("录入心情数据异常={}",moodForm.getPhone()+e.getMessage());
            resultVo = AjaxResultVOUtils.error(e.getMessage());
        }
        return gson.toJson(resultVo);

    }

    /**
     * 查询心情历史记录
     * @param phone
     * @return
     */
    @GetMapping("/history")
    public ResultVO<List<MoodDto>> history(HttpServletRequest request){

        List<MoodDto> historyList;
        
        String message;




        try {

            

            String phone = (String) request.getSession().getAttribute("user");
            historyList = moodFacadeService.findMoodListByOpenid(phone);
            
            message = getMessage(historyList);

        }catch (Exception e){
            log.error("查询心情历史记录={}",e.getMessage());
            return ResultVOUtils.error(60004,e.getMessage());
        }

        return ResultVOUtils.success(historyList,message);
    }

    private String getMessage(List<MoodDto> historyList) {

        String message;

        for (MoodDto moodDto : historyList){
            if (moodDto.getMorningMood().equals("3") || moodDto.getNoonMood().equals("3") || moodDto.getNightMood().equals("3")){
                message = "您最近心情不太好，请注意笑口常开哦";
                return message;
            }
        }
        message ="您最近心情不错，请注意保持哦";
        return message;
    }


    /**
     * 查询监护人历史记录
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/custody")
    public ResultVO<List<MoodDto>> custodyHistory(@RequestParam("phone") String phone,
                                                      @RequestParam("password")String password){


        try {

            /**根据手机号码和密码验证用户是否正确*/
            User user = userFacadeService.findUserByPhone(phone);

            password = EncryptUtils.encrypt(password);

            if (null == user || !password.equals(user.getPassword())){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.USER_ERROE.getCode(),
                        HealthOTWechatErrorCode.USER_ERROE.getMessage());
            }

            return  history(null);



        }catch (Exception e){
            log.error("【查询监护人异常】={}",e.getMessage());
            return ResultVOUtils.error(60004,e.getMessage());

        }

    }


}
