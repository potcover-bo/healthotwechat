package com.xust.healthotwechat.convert;

import com.xust.healthotwechat.dto.MoodDto;
import com.xust.healthotwechat.entity.Mood;
import com.xust.healthotwechat.form.MoodForm;
import com.xust.healthotwechat.utils.DateUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Created by evildoerdb_ on 2018/5/9
 *
 * 心情数据转换service
 */

@Service
public class MoodConvertService {


    /**
     * 心情 form 转换为 entity
     * @param moodForm
     * @return
     */
    public Mood formToEntity(MoodForm moodForm){
        Mood mood = new Mood();

        BeanUtils.copyProperties(moodForm,mood);

        mood.setCreateTime(new Date());

        return mood;
    }

    /**
     * entity转换为dto
     * @param moodList
     * @return
     */
    public /*List<MoodDto>*/MoodDto entityToDto(List<Mood> moodList){
        //List<MoodDto> historyList = new ArrayList<>(moodList.size());
        MoodDto moodDto = new MoodDto();
        double times = moodList.size() * 3;

        double good = 0;
        double general =0;
        double poor = 0;


//        for (Mood mood : moodList){
//            MoodDto moodDto = new MoodDto();
//
//            moodDto.setMorningMood(mood.getMorningMood());
//            moodDto.setNoonMood(mood.getNoonMood());
//            moodDto.setNightMood(mood.getNightMood());
//
//            moodDto.setCreateTime(DateUtils.dateToString(mood.getCreateTime()));
//
//            historyList.add(moodDto);
//        }
        for (Mood mood : moodList){
            //MoodDto moodDto = new MoodDto();

            switch (mood.getMorningMood()){
                case "1":
                    good++;
                    break;
                case "2":
                    general++;
                    break;
                case "3":
                    poor++;
                    break;
                default:
                    break;
            }

            switch (mood.getNoonMood()){
                case "1":
                    good++;
                    break;
                case "2":
                    general++;
                    break;
                case "3":
                    poor++;
                    break;
                default:
                    break;
            }

            switch (mood.getNightMood()){
                case "1":
                    good++;
                    break;
                case "2":
                    general++;
                    break;
                case "3":
                    poor++;
                    break;
                default:
                    break;
            }


        }
        moodDto.setGood((int)(good/times * 100));
        moodDto.setGeneral((int)(general/times * 100));
        moodDto.setPoor((int)(poor/times * 100));
        return moodDto;
    }
}
