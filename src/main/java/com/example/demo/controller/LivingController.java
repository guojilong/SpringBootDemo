package com.example.demo.controller;

import com.example.demo.model.BaseResp;
import com.example.demo.model.LiveInfo;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LivingController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping(name = "/getLiveInfo")
    public BaseResp<LiveInfo> getLiveInfo(String userName) {

        if (StringUtils.isEmpty(userName)) {
            Response.err("userName cannot be null", 500);
        }

        String key = "key." + userName + ".live.info";

        return Response.ok(redisTemplate.opsForValue().get(key));
    }


    @RequestMapping(name = "/liveinfo", method = RequestMethod.POST)
    public BaseResp setLiveInfo(String userName, String pushUrl) {

        if (StringUtils.isEmpty(userName)) {
            Response.err("userName cannot be null", 500);
        }

        if (StringUtils.isEmpty(pushUrl)) {
            Response.err("pushUrl cannot be null", 500);
        }

        String key = "key." + userName + ".live.info";


        LiveInfo liveInfo = new LiveInfo();
        liveInfo.setPushUrl(pushUrl);
        redisTemplate.opsForValue().set(key, liveInfo);


        return Response.ok(null);
    }
}
