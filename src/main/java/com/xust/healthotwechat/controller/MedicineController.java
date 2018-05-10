package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.exception.HealthOTWechatErrorCode;
import com.xust.healthotwechat.exception.HealthOTWechatException;
import com.xust.healthotwechat.facade.MedicineFacadeService;
import com.xust.healthotwechat.form.MedicineForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 药物controller
 */

@Controller
@Slf4j
@RequestMapping("/medicine")
public class MedicineController {

    @Autowired
    private MedicineFacadeService medicineFacadeService;


    /**
     * 录入服药数据
     * @param medicineForm
     * @param bindingResult
     * @return
     */
    @RequestMapping("/entry")
    public ModelAndView entry(@Valid MedicineForm medicineForm,
                              BindingResult bindingResult){

        ModelAndView modelAndView = new ModelAndView();

        /**表单校验异常*/
        if (bindingResult.hasErrors()){
            throw  new RuntimeException(bindingResult.getAllErrors().toString());
        }

        try {
            /**录入数据*/
            int result = medicineFacadeService.entry(medicineForm);

            if(result == -1){
                throw new HealthOTWechatException(HealthOTWechatErrorCode.MEDICINE_DATA_ENTRY_ERROE.getCode(),
                        HealthOTWechatErrorCode.MEDICINE_DATA_ENTRY_ERROE.getMessage());
            }

            modelAndView.setViewName("redirect:/index.html");
        }catch (Exception e){
            log.error("录入服药数据异常={}",e.getMessage());
            modelAndView.setViewName("redirect:/error.html");
        }

        return modelAndView;
    }
}
