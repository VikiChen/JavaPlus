package com.viki.javaplus.zookeeper.Basics;

import java.text.SimpleDateFormat;
import java.util.Date;

//生成订单号规则 使用时间戳+业务id
public class OrderNumGenerator {
    private static int count=0;   //一定要加static 关键字

    public String getNumber() {
        //------------ 模拟线程安全问题
		try {
			Thread.sleep(200);
		} catch (Exception e) {

		}
		//----------------
        SimpleDateFormat simpt = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
        return simpt.format(new Date()) + "-" + ++count;
    }
}
