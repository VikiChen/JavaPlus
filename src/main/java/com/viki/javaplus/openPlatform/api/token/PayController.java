package com.viki.javaplus.openPlatform.api.token;

import java.util.UUID;

import com.viki.javaplus.openPlatform.accessToken.service.BaseApiService;
import com.viki.javaplus.openPlatform.accessToken.service.ResponseBase;
import com.viki.javaplus.openPlatform.accessToken.utils.BaseRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

@RestController
public class PayController extends BaseApiService {
	@Autowired
	private BaseRedisService baseRedisService;

	private static final Long TOKENTIME = (long) (30 * 60);

	// 先获取参数接口,返回令牌
	// 使用令牌传递参数 (不是前端调用是服务器调用)
	@RequestMapping("/getPayToken")
	public String getPayToken(Long userId, Long money) {
		// 生成令牌
		String payToken = UUID.randomUUID().toString();
		// 存放在redis中
		baseRedisService.setString(payToken, userId + "---" + money, TOKENTIME);
		return payToken;
	}

	@RequestMapping("/pay")
	public ResponseBase pay(String payToken) {
		if (StringUtils.isEmpty(payToken)) {
			return setResultError("token 不能为空!");
		}
		String result = (String) baseRedisService.getString(payToken);
		if (StringUtils.isEmpty(result)) {
			return setResultError("参数不能为空!");
		}
		// 直接处理操作数据库
		return setResultSuccessData(result);
	}

}
