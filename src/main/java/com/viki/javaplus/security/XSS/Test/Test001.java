package com.viki.javaplus.security.XSS.Test;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

public class Test001 {   //预防xss 攻击 进行转移测试
    public static void main(String[] args) {
        String name ="<script>";
        System.out.println(StringEscapeUtils.escapeHtml3(name));
    }
}
