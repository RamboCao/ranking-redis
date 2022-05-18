package com.rank.mq.listener;

import com.rank.cache.component.CachePushManager;
import com.rank.dto.CanalJson;
import com.rank.utils.JsonUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * @author Caolp
 */
@Component
public class RabbitmqListener {

    @Autowired
    CachePushManager cachePushManager;

    @RabbitListener(queues = "canal-test")
    public void receiveCacheMessage(String content){

        // 消息是一条一条的推送过来的
        CanalJson canalJson = JsonUtil.parseObject(content);

        // 使用线程池和阻塞队列实现 cache 的推送

        // TODO 这个地方可以进行一下过滤，读取配置，配置中的字段更新了才执行 Cache 推送，否则丢弃
        cachePushManager.pushCache(Collections.singletonList(canalJson));
    }

}