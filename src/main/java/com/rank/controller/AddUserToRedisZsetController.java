package com.rank.controller;

import com.rank.dto.User;
import com.rank.utils.redis.RedisZsetUtil;
import io.lettuce.core.ScoredValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Caolp
 */
@ResponseBody
@RestController
@RequestMapping("api/redis-user")
public class AddUserToRedisZsetController {

    @Autowired
    RedisZsetUtil<String, Long> redisZsetUtil;

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public void addUser(){
        redisZsetUtil.add(1000L + ":" + "score", 1L, 89);
        redisZsetUtil.add(1000L + ":" + "score", 2L, 62);
        redisZsetUtil.add(1000L + ":" + "score", 3L, 65);
        redisZsetUtil.add(1000L + ":" + "score", 4L, 77);
        redisZsetUtil.add(1000L + ":" + "score", 5L, 94);
        redisZsetUtil.add(1000L + ":" + "score", 6L, 87);
    }
}
