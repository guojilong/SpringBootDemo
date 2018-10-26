package com.example.demo.utils;

import org.springframework.beans.factory.annotation.Value;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisClient {


    private static JedisPool jedisPool;
    @Value("${spring.redis.jedis.pool.max-active}")
    private static String REDIS_MAXACTIVE;


    @Value("${spring.redis.jedis.pool.max-idle}")
    private static String REDIS_MAXIDLE;

    @Value("${spring.redis.jedis.pool.max-wait}")
    private static String REDIS_MAXWAIt;

//    @Value("${spring.redis.jedis.pool.}")
//    private static String REDIS_TESTONBORROW ;


    @Value("${spring.redis.host}")
    private static String REDIS_HOST;

    @Value("${spring.redis.port}")
    private static String REDIS_PORT;

    @Value("${spring.redis.timeout}")
    private static String REDIS_TIMEOUT;

    @Value("${spring.redis.password}")
    private static String REDIS_PASSWORD;

    static {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(Integer.parseInt(REDIS_MAXACTIVE));
        config.setMaxIdle(Integer.parseInt(REDIS_MAXIDLE));
        config.setMaxWaitMillis(Integer.parseInt(REDIS_MAXWAIt));
//        config.setTestOnBorrow(Boolean.parseBoolean(REDIS_TESTONBORROW));

        jedisPool = new JedisPool(config, REDIS_HOST, Integer.parseInt(REDIS_PORT), Integer.parseInt(REDIS_TIMEOUT), REDIS_PASSWORD);


    }

    public static synchronized Jedis getJedis(){
        if (jedisPool!=null) {

            Jedis resourse=jedisPool.getResource();
            return resourse;
        }else{
            return null;
        }
    }

    public static String set(String key, Object value) {

        return null;
    }

    public static String set(String key, Object value, long expireTime) {


        return null;
    }


    public static Object get(String key) {

       Jedis jedis=jedisPool.getResource();
       byte[] bytes=jedis.get(key.getBytes());
       jedis.close();

        return null;
    }
}
