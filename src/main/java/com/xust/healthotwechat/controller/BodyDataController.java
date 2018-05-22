package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.AjaxResultVo;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.dto.BodyDataDto;
import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.BodyDataFacadeService;
import com.xust.healthotwechat.facade.UserFacadeService;
import com.xust.healthotwechat.form.BodyDataForm;
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
 * 身体指数controller
 */
@RestController
@RequestMapping("/bodydata")
@Slf4j
public class BodyDataController {


    @Autowired
    private BodyDataFacadeService bodyDataFacadeService;

    @Autowired
    private UserFacadeService userFacadeService;

    @PostMapping("/entry")
    public String  entry(@Valid BodyDataForm bodyDataForm,
                         BindingResult bindingResult,
                         HttpServletRequest request){

        Gson gson = new Gson();

        AjaxResultVo resultVo;


        try {


            String phone = (String) request.getSession().getAttribute("user");

            /**如果参数校验出错*/
            if(bindingResult.hasErrors()){
                throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
            }

            bodyDataForm.setPhone(phone);

            bodyDataFacadeService.entry(bodyDataForm);
            resultVo = AjaxResultVOUtils.success();

        }catch (Exception e){
            log.error("身体数据录入异常={}",bodyDataForm.getPhone()+e.getMessage());
            resultVo = AjaxResultVOUtils.error(e.getMessage());

        }
        return gson.toJson(resultVo);
    }


    /**
     * 根据openid查询历史记录   绘制图标
     * @param phone
     * @return
     */
    @GetMapping("/history")
    public ResultVO<List<BodyDataDto>> history(@RequestParam("phone")String phone,HttpServletRequest request){



        List<BodyDataDto> historyList;

        try {

            if (request !=null){
                String sessionPhone = (String) request.getSession().getAttribute("user");
                if (!sessionPhone.equals(phone)){
                    throw new HealthOTWechatException(HealthOTWechatErrorCode.USER_PHONE_ERROR.getCode(),
                            HealthOTWechatErrorCode.USER_PHONE_ERROR.getMessage());
                }
            }

            /**查询历史记录*/
            historyList = bodyDataFacadeService.findBodyDataListByOpenid(phone);

        }catch (Exception e){
            log.error("【查询身体数据异常】={}",e.getMessage());

            return ResultVOUtils.error(60003,e.getMessage());
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
    public ResultVO<List<BodyDataDto>> custodyHistory(@RequestParam("phone") String phone,
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
            return ResultVOUtils.error(60003,e.getMessage());

        }

    }
}
