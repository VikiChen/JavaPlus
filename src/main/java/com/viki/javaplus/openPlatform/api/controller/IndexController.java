package com.viki.javaplus.openPlatform.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.URLDecoder;
import java.net.URLEncoder;

//http协议特殊字符处理
@Controller
public class IndexController {

    //特殊字符转码
    @RequestMapping("/indexPage")   //参数传入 URLEncoder.encode("");   解析参数  URLDecoder.decode("");
    public String indexPage(String userName){
        return userName;
    }
}
