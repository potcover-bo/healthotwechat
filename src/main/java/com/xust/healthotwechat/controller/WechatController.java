package com.xust.healthotwechat.controller;

import com.xust.healthotwechat.config.WechatConfig;
import com.xust.healthotwechat.config.WechatUrlConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * Created by evildoerdb_ on 2018/5/10
 */
@RequestMapping("/wechat")
@Controller
@Slf4j
public class WechatController {

    /**
     * 微信测试号的认定
     *
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("/portal")
    public void portal(HttpServletRequest request,HttpServletResponse response) throws Exception{

//        String signature = request.getParameter("signature");
//        String timestamp = request.getParameter("timestamp");
//        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");



        PrintWriter pw = response.getWriter();
        pw.append(echostr);
        pw.flush();
    }

}
