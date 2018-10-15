package com.viki.javaplus.openPlatform.OAuth2.controller;

import com.alibaba.fastjson.JSONObject;
import com.viki.javaplus.openPlatform.OAuth2.utils.HttpClientUtils;
import com.viki.javaplus.openPlatform.OAuth2.utils.WeiXinUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OauthController {

    @Autowired
    WeiXinUtils weiXinUtils;

    @RequestMapping("/oauthUrl")
public String oauthUrl(){
        String authorizedUrl = weiXinUtils.getAuthorizedUrl();
        return "redirect:"+authorizedUrl;
    }

    @RequestMapping("/callback")
    public String callBack(String code){
        if(StringUtils.isEmpty(code)){
            return "errorPage";
        }
        String accessTokenUrl = weiXinUtils.getAccessTokenUrl(code);
        JSONObject jsonObject = HttpClientUtils.httpGet(accessTokenUrl);
        boolean errcode = jsonObject.containsKey("errcode");
        if(errcode){
            return "errorPage";
        }
        String access_token = jsonObject.getString("access_token");
        if(StringUtils.isEmpty(access_token)){
            return "errorPage";
        }
        String openid = jsonObject.getString("openid");
        if (StringUtils.isEmpty(openid)){
            return "errorPage";
        }
        String userInfo = weiXinUtils.getUserInfo(access_token, openid);
        HttpClientUtils.httpGet(userInfo);

        return "success";
    }
}
