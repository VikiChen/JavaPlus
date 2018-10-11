package com.viki.javaplus.security.CSRF.Token;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//token 保证幂等   调取API需要先获取token   建议时效2小时
public class TokenUtils {

   private static Map<String,Object> tokenMaps=new ConcurrentHashMap<String,Object>();  //建议使用redis

    // 分布式情况下不能使用synchronized 使用分布式锁
    public static  String getToken(){
        String token="token"+System.currentTimeMillis();
        //hasMap 好处可以附带值
        tokenMaps.put(token,token);
        return token;
    }

    public static Boolean findToken(String tokenKey){
        //判断令牌是否在tokenMap 中是否存在
        String token =(String)tokenMaps.get(tokenKey);
        if(StringUtils.isEmpty(token)){
            return false;
        }
        //获取成功后删除 tokenMapstoken
        tokenMaps.remove(token);
        return true;
    }
}
