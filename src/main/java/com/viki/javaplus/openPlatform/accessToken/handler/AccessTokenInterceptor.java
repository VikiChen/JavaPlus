package com.viki.javaplus.openPlatform.accessToken.handler;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.viki.javaplus.openPlatform.accessToken.service.BaseApiService;
import com.viki.javaplus.openPlatform.accessToken.utils.BaseRedisService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.deser.Deserializers.Base;

//验证AccessToken 是否正确
@Component
public class AccessTokenInterceptor extends BaseApiService implements HandlerInterceptor {
	@Autowired
	private BaseRedisService baseRedisService;

	/**
	 * 进入controller层之前拦截请求
	 * 
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @param o
	 * @return
	 * @throws Exception
	 */

	public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o)
			throws Exception {
		System.out.println("---------------------开始进入请求地址拦截----------------------------");
		String accessToken = httpServletRequest.getParameter("accessToken");
		// 判断accessToken是否空
		if (StringUtils.isEmpty(accessToken)) {
			// 参数Token accessToken
			resultError(" this is parameter accessToken null ", httpServletResponse);
			return false;
		}
		String appId = (String) baseRedisService.getString(accessToken);
		if (StringUtils.isEmpty(appId)) {
			// accessToken 已经失效!
			resultError(" this is  accessToken Invalid ", httpServletResponse);
			return false;
		}
		// 正常执行业务逻辑...
		return true;

	}

	public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o,
			ModelAndView modelAndView) throws Exception {
		System.out.println("--------------处理请求完成后视图渲染之前的处理操作---------------");
	}

	public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			Object o, Exception e) throws Exception {
		System.out.println("---------------视图渲染之后的操作-------------------------0");
	}

	// 返回错误提示
	public void resultError(String errorMsg, HttpServletResponse httpServletResponse) throws IOException {
		PrintWriter printWriter = httpServletResponse.getWriter();
		printWriter.write(new JSONObject().toJSONString(setResultError(errorMsg)));
	}

}