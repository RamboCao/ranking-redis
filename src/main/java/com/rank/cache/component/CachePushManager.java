package com.rank.cache.component;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.rank.dto.CanalJson;
import com.rank.utils.redis.RedisZsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * @author Caolp
 */
@Component
@Slf4j
public class CachePushManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CachePushManager.class);

    private static final String UPDATE = "UPDATE";
    private static final String INSERT = "INSERT";
    private static final String DELETE = "DELETE";

    /**
     * 采用构造注入的方式
     */
    final RedisZsetUtil<String, Long> redisZsetUtil;

    public CachePushManager(RedisZsetUtil<String, Long> redisZsetUtil) {
        this.redisZsetUtil = redisZsetUtil;
    }

    private static final Map<String, List<String>> NEED_CHANGED_TABLE_COLUMNS = CacheConfigReader.configTableColumns();

    public void pushCache(List<CanalJson> canalJsons) {

        long start = System.currentTimeMillis();
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("cache synchronize process thread pool").build();

        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0,
                TimeUnit.MILLISECONDS, new LinkedBlockingDeque<>(1000), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        canalJsons.forEach(canalJson -> executorService.submit(() -> {updateCache(canalJson);}));
        executorService.shutdown();

        try {
            boolean termination = executorService.awaitTermination(1800, TimeUnit.SECONDS);
            LOGGER.info("threadPool is termination: " + termination);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LOGGER.debug("total push Time:{} millis", System.currentTimeMillis() - start);

    }

    public void updateCache(CanalJson canalJson) {

        // 获取所有配置的缓存的信息，仅仅对这些配置中的表和信息进行更新
        LOGGER.info("cache changed: " + canalJson.toString());
        String table = canalJson.getTable();

        List<String> columns = NEED_CHANGED_TABLE_COLUMNS.get(table);

        String type = canalJson.getType();
        switch (type) {
            case UPDATE:
                updateCacheData(canalJson, table, columns);
                break;
            case INSERT:
                insertCacheData(canalJson, table, columns);
                break;
            case DELETE:
                deleteCacheData(canalJson, table, columns);
                break;
            default:
                throw new RuntimeException("type is not recognized");
        }

    }

    private void updateCacheData(CanalJson canalJson, String table, List<String> columns) {
        long gameId = 1000L;
        Long id =  Long.valueOf(canalJson.getData().get(0).get("id").toString());

        for (String column : columns) {
            double v = Double.parseDouble(canalJson.getData().get(0).get(column).toString());
            redisZsetUtil.update(gameId + ":" + column, id, v);
        }
    }

    private void insertCacheData(CanalJson canalJson, String table, List<String> columns) {
        long gameId = 1000L;
        Long id =  Long.valueOf(canalJson.getData().get(0).get("id").toString());

        for (String column : columns) {
            double v = Double.parseDouble(canalJson.getData().get(0).get(column).toString());
            redisZsetUtil.add(gameId + ":" + column, id, v);
        }
    }

    private void deleteCacheData(CanalJson canalJson, String table, List<String> columns) {
        long gameId = 1000L;
        Long id =  Long.valueOf(canalJson.getData().get(0).get("id").toString());

        for (String column : columns) {
            double v = Double.parseDouble(canalJson.getData().get(0).get(column).toString());
            redisZsetUtil.del(gameId + ":" + column, id);
        }
    }
}
