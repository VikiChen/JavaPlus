package com.viki.javaplus.job.xxlJob.handler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

/**
 * 任务Handler的一个Demo（Bean模式）
 *
 * 开发步骤： 1、继承 “IJobHandler” ； 2、装配到Spring，例如加 “@Service” 注解； 3、加 “@JobHander”
 * 注解，注解value值为新增任务生成的JobKey的值;多个JobKey用逗号分割; 4、执行日志：需要通过 "XxlJobLogger.log"
 * 打印执行日志；
 *
 * @author xuxueli 2015-12-19 19:43:36
 */
@JobHander(value = "myJobHandler")
@Service
public class MyJobHandler  extends IJobHandler {
	@Value("${xxl.job.executor.port}")
	private String port;
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		System.out.println("myJobHandler,port:"+port);
		return ReturnT.SUCCESS;
	}

}
