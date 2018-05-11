package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.VO.ResultVO;
import com.xust.healthotwechat.entity.User;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.UserFacadeService;
import com.xust.healthotwechat.form.UserForm;
import com.xust.healthotwechat.utils.EncryptUtils;
import com.xust.healthotwechat.utils.ResultVOUtils;
import com.xust.healthotwechat.utils.SmsUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


/**
 * Created by evildoerdb_ on 2018/5/6
 *
 * 用户controller
 */

@Controller
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserFacadeService userFacadeService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 注册
     * @param userForm
     * @param bindingResult
     * @return  返回登录页面
     */
    @RequestMapping("/register")
    public ModelAndView register(@Valid UserForm userForm,
                                 BindingResult bindingResult,
                                 Map<String,Object> map){

        try{
            /**用户校验出错*/
            if (bindingResult.hasErrors()){
                throw new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
            }

            /**校验验证码是否正确*/
//            if (!checkValidateCode(userForm.getValidateCode(),userForm.getPhone())){
//                throw new HealthOTWechatException(HealthOTWechatErrorCode.VALIDATE_CODE_ERROE.getCode(),
//                        HealthOTWechatErrorCode.VALIDATE_CODE_ERROE.getMessage());
//            }

            /**查询用户是否存在*/
            boolean isExist = userFacadeService.userIsExist(userForm.getPhone());

            /**用户已经存在*/
            if (isExist){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.USER_ISEXIST_ERROE.getCode(),
                        HealthOTWechatErrorCode.USER_ISEXIST_ERROE.getMessage());
            }

            /**进行注册*/
            userFacadeService.register(userForm);
            map.put("message","注册成功");
            map.put("url","login");


        }catch (Exception e){
            map.put("message",e.getMessage());
            map.put("url","register");
            return new ModelAndView("common/error",map);
        }
        return new ModelAndView("common/success",map);
    }


    /**
     * 生成验证码
     * @param phone
     * @return
     */
    @RequestMapping("generateValidateCode")
    @Transactional
    public ResultVO<String> generateValidateCode(@RequestParam("phone") String phone){

        try {
            /**生成六位随机验证码*/
            String validateCode = SmsUtils.verificationCode();

            /**redis中加入验证码 过期时间为两分钟*/
            redisTemplate.opsForValue().set(phone+"validateCode",validateCode,120,TimeUnit.SECONDS);

            //TODO  调用短信平台发送验证码

        }catch (Exception e){
            log.error("【发送验证码失败】={}",e.getMessage());
           return ResultVOUtils.error("233","发送验证码失败，请重新发送");
        }

        return ResultVOUtils.success();

    }


    /**
     * 校验验证码是否正确
     * @param code
     * @param phone
     * @return
     */
    private boolean checkValidateCode(String code,String phone){

        /**从redis中获取验证码*/
        String value = (String) redisTemplate.opsForValue().get(phone+"validateCode");

        /**如果验证码为空或者验证码错误*/
        if (null == value || !value.equals(code)){
            return false;
        }

        return true;
    }


    /***
     * 根据手机号码修改密码
     * @param phone
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @RequestMapping("updatepassword")
    public ModelAndView changePassword(@RequestParam("phone") String phone,
                                   @RequestParam("oldPassword") String oldPassword,
                                   @RequestParam("newPassword") String newPassword,
                                   Map<String,Object> map){
        try {
            /**先去查询密码是否正确*/
            oldPassword = EncryptUtils.encrypt(oldPassword);

            User user = userFacadeService.findUserByPhone(phone);
            if (null == user || !oldPassword.equals(user.getPassword())){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.USER_ERROE.getCode(),
                        HealthOTWechatErrorCode.USER_ERROE.getMessage());
            }


            /** 先对密码进行加密 再去数据库修改密码*/
            newPassword = EncryptUtils.encrypt(newPassword);
            userFacadeService.updatePassword(phone,newPassword);

            map.put("message","密码修改成功");
            map.put("url","index.html");

        }catch (RuntimeException e){
            log.error("【修改密码异常】={}",e.getMessage());
            map.put("message",e.getMessage());
            map.put("url","index.html");
            return new ModelAndView("common/error",map);

        }

        return new ModelAndView("common/success",map);
    }

}
