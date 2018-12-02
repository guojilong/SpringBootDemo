package com.example.demo.controller;


import com.example.demo.model.BaseResp;
import com.example.demo.model.VideoInfo;
import com.example.demo.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GlassController {

    @Autowired
    private RedisTemplate redisTemplate;


    @RequestMapping(value = "/video/history", method = RequestMethod.GET)
    public BaseResp<VideoInfo> getPlayHistory(String userName) {

        if (StringUtils.isEmpty(userName)) {
            return Response.err("username not empty!", 500);
        }

        String key = "key." + userName + ".video.info";

        VideoInfo videoInfo = (VideoInfo) redisTemplate.opsForValue().get(key);


        return Response.ok(videoInfo);
    }

    @RequestMapping(value = "/video/update", method = RequestMethod.POST)
    public BaseResp updatePlayHistory(String videoUrl,long position, String userName) {


        if (StringUtils.isEmpty(userName)) {
            return Response.err("username not empty!", 500);
        }

        VideoInfo videoInfo=new VideoInfo();
        videoInfo.setPosition(position);
        videoInfo.setUrl(videoUrl);

        String key = "key." + userName + ".video.info";

        redisTemplate.opsForValue().set(key, videoInfo);


        return Response.ok(null);
    }
}
