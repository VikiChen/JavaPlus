package com.viki.javaplus.conifgCenter.Controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//apollo 分布式配置中心
@RestController
public class IndexController {

    @Value("${test:viki}")   //加上默认值
    private String test;

    @RequestMapping("/getTest")
    public String getTest() {
        return test;
    }

}
