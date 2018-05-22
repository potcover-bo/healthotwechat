package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.config.MenuNameConfig;
import com.xust.healthotwechat.config.MenuUrlConfig;
import com.xust.healthotwechat.quartz.*;
import com.xust.healthotwechat.wechat.WechatUtils;
import com.xust.healthotwechat.wechat.model.AccessTokenModel;
import com.xust.healthotwechat.wechat.model.MenuCreateModel;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * Created by evildoerdb_ on 2018/5/10
 *
 * 初始化菜单controller
 */
@Component
@Order(value = 1)
@Slf4j
public class InitMenuController implements ApplicationRunner {



    @Autowired
    private WechatUtils wechatUtils;

    @Autowired
    private MenuNameConfig menuNameConfig;

    @Autowired
    private MenuUrlConfig menuUrlConfig;




    @Override
    public void run(ApplicationArguments applicationArguments) {

        System.out.println("欢迎使用健康管理项目");
        System.out.println("项目启动了。。。。哈哈哈");
//
//        try {
//            SendSmsResponse sendSmsResponse = SmsUtils.sendSms("18789449429","123456");
//            System.out.println("code = " +sendSmsResponse.getCode());
//            System.out.println("message = " +sendSmsResponse.getMessage());
//            System.out.println("requestid = " + sendSmsResponse.getRequestId());
//            System.out.println("bizid = " + sendSmsResponse.getBizId());
//            System.out.println("发送成功");
//        } catch (ClientException e) {
//            System.out.println("发送失败");
//            e.printStackTrace();
//        }

        //初始化菜单
        //initWechatMenu();

        //开启定时任务
        //startJob();


    }

    /**
     * 开启定时任务
     */
    private void startJob() {
        try {

            //开启六个定时任务
            quartz("bloodPressure",BloodPressureJob.class);
            quartz("bloodSugar",BloodSugarJob.class);
            quartz("bodyData",BodyDataJob.class);
            quartz("medicine",MedicineJob.class);
            quartz("mood",MoodJob.class);
            quartz("slleeping",SleepingJob.class);



        } catch (Exception e) {
            log.error("【开启定时任务异常】= {}",e.getMessage());
        }
    }


    /**
     * 定时任务
     * @param name  job的名字
     * @param clazz 集成job的Class
     * @throws ParseException
     * @throws SchedulerException
     */
    public void quartz(String name, Class<? extends Job> clazz) throws ParseException, SchedulerException {
        JobDetailImpl job = new JobDetailImpl();
        job.setName(name);
        job.setJobClass(clazz);


        //cron表达式
        CronTriggerImpl trigger = new CronTriggerImpl();
        trigger.setName(name);
        //每天中午的十二点执行一次
        trigger.setCronExpression("0 0 12 1/1 * ? ");

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();

        scheduler.start();;
        scheduler.scheduleJob(job,trigger);

    }



    /**
     * 初始化菜单
     */
//    @ResponseBody
//    @RequestMapping("/config")
    public void initWechatMenu(){


        /**获取accesstoken*/
        AccessTokenModel accessTokenModel =wechatUtils.getAccessToken();

        /**创建菜单*/
        MenuCreateModel menu = wechatUtils.createMenu(accessTokenModel.getAccess_token(),menuNameConfig,menuUrlConfig);

        if(menu.getErrcode().equals("0")){
            System.out.println("666 创建菜单成功");
        }else {
            System.out.println("23333333333");
        }

    }
}
