package com.viki.javaplus.redis.Basic;

import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class RedisTest {
    private static Jedis jedis=null;

    public static void main(String[] args) {
        jedis=new Jedis("127.0.0.1",6379);
//        jedis.auth("123456");
        setString();
        setMap();
        setList();
        setSet();
    }

    public static void setString(){           //string
        jedis.set("name","12234");
    }

    public static void setMap(){             //hash
        HashMap<String, String> map = new HashMap<>();
        map.put("sds","sd");
        map.put("sd","sdsd");
        jedis.hmset("sdd",map);
    }

    public static void setList(){                   //list
        jedis.lpush("bbb","222");
        jedis.lpush("bbb","222222");
    }

    public static void setSet(){    //无序集合  set
        jedis.sadd("aaa","wdwd");
        jedis.sadd("aaa","sda");
        jedis.sadd("aaa","sd");
    }

}
