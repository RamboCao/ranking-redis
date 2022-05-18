package com.rank.cache.job;

import com.rank.cache.component.CachePushManager;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Caolp
 * 每 5s 中运行一次任务，拉取一下数据
 * 这个其实和 rabbitmq 之间存在冲突，rabbmit 发现改变则会将数据放到队列中，然后去等待消费
 * 以格式手动，一个是自动，自动实现依靠 cancl server
 */
@Component
@Slf4j
public class SimpleCanalClientJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleCanalClientJob.class);

    private static final String INCREASE = "increase";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    CachePushManager cachePushManager;

    /**
     * 必须在启动类上添加 @EnableScheduling 注解，定时任务才能生效
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void execute(){
        LOGGER.debug("CanalClientJob start at " + DATE_FORMAT.format(new Date()));
//        cachePushManager.pushCache();
    }

}
