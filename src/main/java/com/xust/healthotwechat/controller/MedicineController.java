package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.facade.MedicineFacadeService;
import com.xust.healthotwechat.form.MedicineForm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;

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
    @PostMapping("/entry")
    public ModelAndView entry(@Valid MedicineForm medicineForm,
                              BindingResult bindingResult, Map<String,Object> map){


        /**表单校验异常*/
        if (bindingResult.hasErrors()){
            throw  new RuntimeException(bindingResult.getFieldError().getDefaultMessage());
        }

        try {

            medicineFacadeService.entry(medicineForm);
            map.put("message","成功了");
            map.put("url","index.html");


        }catch (Exception e){
            log.error("录入服药数据异常={}",medicineForm.getPhone()+e.getMessage());
            map.put("message",e.getMessage());
            map.put("url","index.html");
            return new ModelAndView("common/error",map);
        }

        return new ModelAndView("common/success",map);
    }
}
