package com.xust.healthotwechat.controller;

import com.google.gson.Gson;
import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.UserFacadeService;
import com.xust.healthotwechat.form.UserForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;


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



        /**用户校验出错*/
        if (bindingResult.hasErrors()){
            throw new RuntimeException(bindingResult.getAllErrors().toString());
        }

        /**校验验证码是否正确*/


        /**查询用户是否存在*/
        boolean isExist = userFacadeService.userIsExist(userForm.getPhone());

        /**用户已经存在*/
        if (isExist){
            map.put("message","用户已经存在");
            map.put("url","login");
            return new ModelAndView("error",map);
        }


        /**进行注册*/
        try{
            int result = userFacadeService.register(userForm);

            if (result == -1){
                throw  new HealthOTWechatException(HealthOTWechatErrorCode.USER_REGISTER_ERROE.getCode(),
                        HealthOTWechatErrorCode.USER_REGISTER_ERROE.getMessage());
            }

        }catch (Exception e){
            map.put("message",e.getMessage());
            map.put("url","register");
            return new ModelAndView("error",map);
        }
        map.put("message","注册成功");
        return new ModelAndView("success",map);
    }
}
