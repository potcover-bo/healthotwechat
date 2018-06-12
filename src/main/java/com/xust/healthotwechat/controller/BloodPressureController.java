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
import com.xust.healthotwechat.utils.SmsUtils;
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

        try {


            String sessionPhone = (String) request.getSession().getAttribute("user");

            /**参数校验出错*/
            if (bindingResult.hasErrors()){
                log.error("【录入血压】参数不正确 bloodPressureForm={}",bloodPressureForm);
                throw  new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
            }
            User user = userFacadeService.findUserByPhone(sessionPhone);

            bloodPressureForm.setPhone(sessionPhone);

            if (Integer.parseInt(bloodPressureForm.getHighPressure())>=150){
                SmsUtils.sendSmsResponse(user.getCustodyRelationship(),user.getCustodyPhone(),bloodPressureForm.getHighPressure());
                bloodPressureForm.setSaveHealthRecord("1");
            }
            if (Integer.parseInt(bloodPressureForm.getLowPressure())<=65){
                SmsUtils.sendSmsResponse(user.getCustodyRelationship(),user.getCustodyPhone(),bloodPressureForm.getLowPressure());
                bloodPressureForm.setSaveHealthRecord("1");
            }

            bloodPressureFacadeService.entryBloodPressure(bloodPressureForm);
            resultVo = AjaxResultVOUtils.success("录入成功");




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
    public ResultVO<List<BloodPressureDto>> getData(HttpServletRequest request){




        List<BloodPressureDto> historyList;

        String message;
        try {


            String phone = (String) request.getSession().getAttribute("user");
            historyList = bloodPressureFacadeService.findBloodPressureList(phone);
            message= getMessage(historyList);


        } catch (Exception e){

            log.error("【查询历史记录异常】={}",e.getMessage());
            return ResultVOUtils.error(60001,e.getMessage());
        }
        return ResultVOUtils.success(historyList,message);

    }

    private String getMessage(List<BloodPressureDto> historyList) {

        String message;
        for (BloodPressureDto bloodPressureDto :historyList){

            if (Integer.parseInt(bloodPressureDto.getHighPressure())>=150 || Integer.parseInt(bloodPressureDto.getLowPressure())<=65){
                message="您最近一周血压偏高，请根据情况及时就医";
                return message;
            }
        }
        message = "您最近血压曲线较为稳定，请注意保持哦";
        return message;
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

            return  getData(null);



        }catch (Exception e){
            log.error("【查询监护人异常】={}",e.getMessage());
            return ResultVOUtils.error(60001,e.getMessage());

        }

    }





}
