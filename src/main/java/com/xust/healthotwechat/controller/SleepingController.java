package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.AjaxResultVo;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.SleepingDto;
import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.SleepingFacadeService;
import com.xust.healthotwechat.facade.UserFacadeService;
import com.xust.healthotwechat.form.SleepingForm;
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
 * 睡眠controller
 */
@RestController
@Slf4j
@RequestMapping("/sleeping")
public class SleepingController {

    @Autowired
    private SleepingFacadeService sleepingFacadeService;

    @Autowired
    private UserFacadeService userFacadeService;

    @PostMapping("/entry")
    public String entry(@Valid SleepingForm sleepingForm,
                        BindingResult bindingResult,
                        HttpServletRequest request){

        Gson gson = new Gson();

        AjaxResultVo resultVo;


        try {


            String phone = (String) request.getSession().getAttribute("user");

            if (bindingResult.hasErrors()){
                throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
            }

            sleepingForm.setPhone(phone);

            sleepingFacadeService.entry(sleepingForm);
            resultVo = AjaxResultVOUtils.success("录入成功");


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
    public ResultVO<List<SleepingDto>> history(HttpServletRequest request){

        List<SleepingDto> historyList;

        String message;


        try {



            String phone = (String) request.getSession().getAttribute("user");
            historyList = sleepingFacadeService.findSleepingListByOpenid(phone);
            message = getMessage(historyList);


        }catch (Exception e){
            log.error("查询睡眠情况={}",e.getMessage());
            return ResultVOUtils.error(60005,e.getMessage());
        }

        return ResultVOUtils.success(historyList,message);
    }

    private String getMessage(List<SleepingDto> historyList) {
        String message;

        for (SleepingDto sleepingDto : historyList){
            if (Integer.parseInt(sleepingDto.getNightTime())<8){
                message = "您最近睡眠时间有点少，请注意休息";
                return message;
            }
        }
        message = "您最近睡眠不错，请继续保持哦";
        return message;
    }


    /**
     * 查询监护人历史记录
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/custody")
    public ResultVO<List<SleepingDto>> custodyHistory(@RequestParam("phone") String phone,
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
            return ResultVOUtils.error(60005,e.getMessage());

        }

    }

}
