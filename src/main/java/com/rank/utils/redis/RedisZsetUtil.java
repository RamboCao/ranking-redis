package com.rank.utils.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Set;

/**
 * @author Caolp
 * Redis Zset 操作类
 */
@Component
@SuppressWarnings("rawtypes, unchecked")
public class RedisZsetUtil<K, V> {

    public static final Logger LOGGER = LoggerFactory.getLogger(RedisZsetUtil.class);

    private static final String ROOT = "ranking-redis";
    private static final String COLON = ":";
    private static final Integer EXPIRE_SECONDS = 3600;

    private static String getRootPath() {
        return ROOT + COLON;
    }

    final RedisTemplate redisTemplate;

    /**
     * 使用构造器进行注入
     *
     * @param redisTemplate redisTemplate
     */
    @Autowired
    public RedisZsetUtil(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    /**
     * 向 zset 中添加一个元素，key, value, score(用来进行排序操作)
     *
     * @param key   key
     * @param value value
     * @param score score
     */
    public Boolean add(K key, V value, double score) {
        return redisTemplate.opsForZSet().add(getRootPath() + key, value, score);
    }

    /**
     * 更新 zset 中的元素
     *
     * @param key
     * @param value
     * @param score
     * @return
     */
    public Boolean update(K key, V value, double score) {
        return redisTemplate.opsForZSet().add(getRootPath() + key, value, score);

    }

    /**
     * 删除 zset 中键值为 key 的元素
     *
     * @param key key
     * @return 删除值的序号
     */
    public Long del(K key, V value) {
        return redisTemplate.opsForZSet().remove(getRootPath() + key, value);
    }

    /**
     * 给指定的 key value 增加 score， score 值可以为正，也可以为负
     *
     * @param key   key
     * @param value value
     * @param score score
     * @return score + incScore
     */
    public Double incScore(K key, V value, double score) {
        return redisTemplate.opsForZSet().incrementScore(getRootPath() + key, value, score);
    }

    /**
     * 查询 value 在 zset 中排名情况
     *
     * @param key   key
     * @param value score
     */
    public Long ranking(K key, V value) {
        return redisTemplate.opsForZSet().rank(getRootPath() + key, value);
    }

    /**
     * 判断某个 key 是否存在
     *
     * @param key key
     * @return true/false
     */
    public Boolean hasKey(K key) {
        return redisTemplate.hasKey(getRootPath() + key);
    }

    /**
     * 查询在一定范围内的 key 集合的排序情况，0-1 表示获取集合全部内容
     *
     * @param key   key
     * @param start 开始位置
     * @param end   结束位置
     * @return 获得的结果集
     */
    public Set<V> rangeRanking(K key, Long start, Long end) {
        return redisTemplate.opsForZSet().range(getRootPath() + key, start, end);
    }

    /**
     * 获取一定范围内 key 集合的排序情况，按照分数较高的倒叙排列
     *
     * @param key   key
     * @param start 开始位置
     * @param end   结束位置
     * @return 获得的结果集倒叙
     */
    public Set<V> revRangeRanking(K key, int start, int end) {
        return redisTemplate.opsForZSet().reverseRange(getRootPath() + key, start, end);
    }

    /**
     * 获取指定 score 区间的排名
     *
     * @param key key
     * @param min 分数最小值
     * @param max 分数最大值
     * @return 获得的结果集和
     */
    public Set<V> rangeRankingByScore(K key, double min, double max) {
        return redisTemplate.opsForZSet().rangeByScore(getRootPath() + key, min, max);
    }
}
