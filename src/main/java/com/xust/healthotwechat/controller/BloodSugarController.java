package com.xust.healthotwechat.controller;

import com.aliyuncs.exceptions.ClientException;
import com.google.gson.Gson;
import com.xust.healthotwechat.VO.AjaxResultVo;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BloodSugarDto;
import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BloodSugarFacadeService;
import com.xust.healthotwechat.facade.UserFacadeService;
import com.xust.healthotwechat.form.BloodSugarForm;
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
 * 血糖controller
 *
 */
@RestController
@RequestMapping("/bloodsugar")
@Slf4j
public class BloodSugarController {

    @Autowired
    private BloodSugarFacadeService bloodSugarFacadeService;

    @Autowired
    private UserFacadeService userFacadeService;

    @PostMapping("/entry")
    public String entry(@Valid BloodSugarForm bloodSugarForm,
                        BindingResult bindingResult,
                        HttpServletRequest request) throws ClientException {

        Gson gson = new Gson();

        AjaxResultVo resultVo;



        try {


            String sessionPhone = (String) request.getSession().getAttribute("user");

            /**参数校验出错*/
            if(bindingResult.hasErrors()){
                throw  new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
            }

            bloodSugarForm.setPhone(sessionPhone);
            User user = userFacadeService.findUserByPhone(sessionPhone);

            if (Double.parseDouble(bloodSugarForm.getBloodSugarValue())>6.1){
                SmsUtils.sendBloodSugar(user.getCustodyRelationship(),user.getCustodyPhone(),bloodSugarForm.getBloodSugarValue());
                bloodSugarForm.setSaveHealthRecord("1");
            }

            if (Double.parseDouble(bloodSugarForm.getBloodSugarValue())<3.8){
                SmsUtils.sendBloodSugar(user.getCustodyRelationship(),user.getCustodyPhone(),bloodSugarForm.getBloodSugarValue());
                bloodSugarForm.setSaveHealthRecord("1");
            }

            bloodSugarFacadeService.entryBloodSugar(bloodSugarForm);
            resultVo = AjaxResultVOUtils.success("录入成功");




        }catch (HealthOTWechatException e){
            log.error("录入血糖={}",bloodSugarForm.getPhone()+e.getMessage());
            resultVo = AjaxResultVOUtils.error(e.getMessage());
        }

        return gson.toJson(resultVo);
    }


    /**
     * 查询血糖历史记录
     * @param phone
     * @return
     */
    @GetMapping("/history")
    public ResultVO<List<BloodSugarDto>> getData(HttpServletRequest request){



        List<BloodSugarDto> historyList ;
        String message;

        try {


            String phone = (String) request.getSession().getAttribute("user");
            historyList = bloodSugarFacadeService.findBloodSugarListByOpenid(phone);
            message = getMessage(historyList);



        } catch (Exception e){

            log.error("【查询历史记录异常】={}",e.getMessage());

            return ResultVOUtils.error(60002,e.getMessage());
        }

        return ResultVOUtils.success(historyList,message);
    }

    private String getMessage(List<BloodSugarDto> historyList) {

        String message;
        for (BloodSugarDto bloodSugarDto : historyList){

            if (Double.parseDouble(bloodSugarDto.getBloodSugarValue())>=6.1 || Double.parseDouble(bloodSugarDto.getBloodSugarValue())<=3.8){
                message = "您最近的血糖值不太稳定，请根据情况及时到医院就诊";
                return message;
            }
        }
        message ="您最近的血糖值较为稳定，请继续保持哦";
        return message;
    }


    /**
     * 查询监护人历史记录
     * @param phone
     * @param password
     * @return
     */
    @PostMapping("/custody")
    public ResultVO<List<BloodSugarDto>> custodyHistory(@RequestParam("phone") String phone,
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
            return ResultVOUtils.error(60002,e.getMessage());

        }

    }
}
