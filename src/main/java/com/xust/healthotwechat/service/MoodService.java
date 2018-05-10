package com.xust.healthotwechat.service;

import com.xust.healthotwechat.entity.Mood;
import com.xust.healthotwechat.mapper.MoodMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 心情service
 */
@Service
public class MoodService {

    @Autowired
    private MoodMapper moodMapper;

    /**
     * 插入一条记录
     * @param mood
     * @return
     */
    public int insert(Mood mood){
        return moodMapper.insert(mood);
    }


    /**
     * 查询历史记录
     * @param phone
     * @return
     */
    public List<Mood> findMoodList(@Param("phone")String phone) {
        return moodMapper.findMoodList(phone);
    }
}
