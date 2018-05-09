package com.xust.healthotwechat.convert;

import com.xust.healthotwechat.entity.Medicine;
import com.xust.healthotwechat.form.MedicineForm;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 药物数据转换serivice
 */
@Service
public class MedicineConvertService {

    /**
     * 药物form转换为 entity
     * @param medicineForm
     * @return
     */
    public Medicine formToEntity(MedicineForm medicineForm){
        Medicine medicine = new Medicine();

        BeanUtils.copyProperties(medicineForm,medicine);

        medicine.setCreateTime(new Date());

        return medicine;
    }
}
