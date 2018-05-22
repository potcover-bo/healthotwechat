package com.xust.healthotwechat.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by evildoerdb_ on 2018/5/8
 *
 * 时间工具包
 */

public class DateUtils {


    /**
     * 获取当前时间在当天中的剩余秒数
     * @return
     */
    public static Integer  getRemainSeconds(){
        long current = System.currentTimeMillis();
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long tomorrowzero = calendar.getTimeInMillis();
        long tomorrowzeroSeconds = (tomorrowzero- current) / 1000;
        return (int) tomorrowzeroSeconds;

    }


    /**
     * 获取当天时间的字符串格式
     * @return
     */
    public  static String nowDateFormat(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(new Date());
    }

    /**
     * 将Date类型的转换成日期字符串
     * @param date
     * @return
     */
    public static String dateToString(Date date){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd");
        return simpleDateFormat.format(date);
    }
}
