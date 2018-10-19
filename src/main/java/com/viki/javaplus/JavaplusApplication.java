package com.viki.javaplus;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@EnableApolloConfig  apollo配置
@SpringBootApplication
@ServletComponentScan
public class JavaplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaplusApplication.class, args);
    }
}
