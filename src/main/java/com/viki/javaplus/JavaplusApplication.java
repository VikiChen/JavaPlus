package com.viki.javaplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class JavaplusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaplusApplication.class, args);
    }
}
