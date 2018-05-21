package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.AjaxResultVo;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BloodPressureDto;
import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BloodPressureFacadeService;
import com.xust.healthotwechat.facade.UserFacadeService;
import com.xust.healthotwechat.form.BloodPressureForm;
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
 * Created by evildoerdb_ on 2018/5/8
 *
 * 血压controller
 */
@RestController
@RequestMapping("/bloodpressure")
@Slf4j
public class BloodPressureController {


    @Autowired
    private BloodPressureFacadeService bloodPressureFacadeService;

    @Autowired
    private UserFacadeService userFacadeService;

    /**
     * 录入血压
     *
     * @param bloodPressureForm
     * @param bindingResult
     */
    @PostMapping("/entry")
    public String entryBloodPressure(@Valid BloodPressureForm bloodPressureForm,
                                           BindingResult bindingResult,
                                     HttpServletRequest request){

        Gson gson = new Gson();

        AjaxResultVo resultVo;

        String sessionPhone = (String) request.getSession().getAttribute("user");

        /**参数校验出错*/
        if (bindingResult.hasErrors()){
            log.error("【录入血压】参数不正确 bloodPressureForm={}",bloodPressureForm);
            throw  new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        bloodPressureForm.setPhone(sessionPhone);

        try {

            bloodPressureFacadeService.entryBloodPressure(bloodPressureForm);
            resultVo = AjaxResultVOUtils.success();


        }catch (Exception e){

            log.error("录入血压={}",bloodPressureForm.getPhone()+e.getMessage());
            resultVo = AjaxResultVOUtils.error(e.getMessage());
        }

        return gson.toJson(resultVo);
    }


    /**
     * 查询血压的历史记录  用于绘制曲线
     * @param phone
     * @return
     */
    @GetMapping("/history")
    public ResultVO<List<BloodPressureDto>> getData(@RequestParam("phone") String phone, HttpServletRequest request){




        List<BloodPressureDto> historyList;
        if (request !=null){
            String sessionPhone = (String) request.getSession().getAttribute("user");
            if (!sessionPhone.equals(phone)){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.USER_PHONE_ERROR.getCode(),
                        HealthOTWechatErrorCode.USER_PHONE_ERROR.getMessage());
            }
        }




        try {

            historyList = bloodPressureFacadeService.findBloodPressureList(phone);


        } catch (Exception e){

            log.error("【查询历史记录异常】={}",e.getMessage());
            return ResultVOUtils.error(60001,e.getMessage());
        }

        return ResultVOUtils.success(historyList);

    }


    /**
     * 查询监护人历史记录
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/custody")
    public ResultVO<List<BloodPressureDto>> custodyHistory(@RequestParam("phone") String phone,
                                                           @RequestParam("password")String password){


        try {

            /**根据手机号码和密码验证用户是否正确*/
            User user = userFacadeService.findUserByPhone(phone);

            password = EncryptUtils.encrypt(password);

            if (null == user || !password.equals(user.getPassword())){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.USER_ERROE.getCode(),
                        HealthOTWechatErrorCode.USER_ERROE.getMessage());
            }

            return  getData(phone,null);



        }catch (Exception e){
            log.error("【查询监护人异常】={}",e.getMessage());
            return ResultVOUtils.error(60001,e.getMessage());

        }

    }





}
