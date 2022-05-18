package com.rank.utils.redis;

import com.rank.utils.CastUtil;
import com.rank.utils.ClassUtil;
import com.rank.utils.StreamUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Caolp
 */
public class RedisUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisUtils.class);
    
    @Autowired
    private static StringRedisTemplate redisTemplate;

    private static final String SCRIPT_PATH = "lua/compare_and_increase.lua";

    private static final String SCRIPT_CONTENT = StreamUtil.getString(ClassUtil.getClassLoader().getResourceAsStream(SCRIPT_PATH));

    private static DefaultRedisScript<Long> script = new DefaultRedisScript<>();

    private static final RedisSerializer ARGS_SERIALIZER = new GenericJackson2JsonRedisSerializer();

    private static final RedisSerializer RESULT_SERIALIZER = new StringRedisSerializer();

    private static final String ROOT = "ranking-redis";
    private static final String COLON = ":";
    private static final Integer EXPIRE_SECONDS = 3600;

    private static String getRootPath() {
        return ROOT + COLON;
    }

    static {
        script.setScriptText(SCRIPT_CONTENT);
        script.setResultType(Long.class);
    }

    private static final String DELANDZADDSCRIPT =
            "if redis.call('zcard', KEYS[1]) > 0 then\n" +
                    "   redis.call('del', KEYS[1])\n" +
                    "   for i, v in pairs(ARGV) do\n" +
                    "       if i > (table.getn(ARGV)) / 2 then\n" +
                    "           break\n" +
                    "       end\n" +
                    "       redis.call('zadd', KEYS[1], ARGV[2*i - 1], ARGV[2*i])\n" +
                    "   end\n" +
                    "   return 1\n" +
                    "else\n" +
                    "   for i, v in pairs(ARGV) do\n" +
                    "       if i > (table.getn(ARGV)) / 2 then\n" +
                    "           break\n" +
                    "       end\n" +
                    "       redis.call('zadd',KEYS[1], ARGV[2*i - 1], ARGV[2*i])\n" +
                    "   end\n" +
                    "   return 1\n" +
                    "end";

    public Long compareAndIncrease(String key, Long limit, Long increase) {
        Object execute = redisTemplate.execute(script, ARGS_SERIALIZER, RESULT_SERIALIZER, Collections.singletonList(key), limit, increase);
        long result = CastUtil.castLong(execute);
        LOGGER.info("key [{}] compare limit {} and increase {} result is {}", key, limit, increase, result);
        return result;
    }

    public Long decrease(String key, Long decreaseNum) {
        Long result = redisTemplate.opsForValue().increment(key, -decreaseNum);
        LOGGER.info("key [{}] decrease {} result is {}", key, decreaseNum, result);
        return result;
    }

    public Long increase(String key, Long increaseNum) {
        Long result = redisTemplate.opsForValue().increment(key, increaseNum);
        LOGGER.info("key [{}] decrease {} result is {}", key, increaseNum, result);
        return result;
    }

    public static Object get(String key, Object hashKey) {
        return redisTemplate.opsForHash().get(getRootPath() + key, hashKey);
    }

    public static void put(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(getRootPath() + key, hashKey, value);
    }

    public static void putAndSetExpire(String key, String hashKey, String value, long time) {
        try {
            redisTemplate.opsForHash().put(getRootPath() + key, hashKey, value);
            if (time > 0) {
                redisTemplate.expire(getRootPath() + key, time, TimeUnit.SECONDS);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除操作
     *
     * @param key
     * @param hashKeys
     */
    public static void delete(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(getRootPath() + key, hashKeys);
    }

    /**
     * 判断是否存在 key
     *
     * @param key
     * @param hashKey
     * @param value
     */
    public static boolean hasKey(String key, Object hashKey, Object value) {
        return redisTemplate.opsForHash().hasKey(getRootPath() + key, hashKey);
    }

    /**
     * 放入一个 map 并设置过期时间
     *
     * @param key  键值
     * @param map  存放的 map
     * @param time 过期时间
     * @return true/false
     */
    public boolean putMapAndSetExpire(String key, Map<String, Object> map, long time) {
        try {
            redisTemplate.opsForHash().putAll(getRootPath() + key, map);
            if (time > 0) {
                redisTemplate.expire(getRootPath() + key, time, TimeUnit.SECONDS);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
