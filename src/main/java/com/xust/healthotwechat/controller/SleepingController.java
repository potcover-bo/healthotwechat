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
    public ResultVO<List<SleepingDto>> history(@RequestParam("phone")String phone,HttpServletRequest request){

        List<SleepingDto> historyList;




        try {

            if (request !=null){
                String sessionPhone = (String) request.getSession().getAttribute("user");
                if (!sessionPhone.equals(phone)){
                    throw new HealthOTWechatException(HealthOTWechatErrorCode.USER_PHONE_ERROR.getCode(),
                            HealthOTWechatErrorCode.USER_PHONE_ERROR.getMessage());
                }
            }

            historyList = sleepingFacadeService.findSleepingListByOpenid(phone);

        }catch (Exception e){
            log.error("查询睡眠情况={}",e.getMessage());
            return ResultVOUtils.error(60005,e.getMessage());
        }


        return ResultVOUtils.success(historyList,"睡眠曲线比较稳定");
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

            return  history(phone,null);



        }catch (Exception e){
            log.error("【查询监护人异常】={}",e.getMessage());
            return ResultVOUtils.error(60005,e.getMessage());

        }

    }

}
