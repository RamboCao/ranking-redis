package com.rank;

import com.rank.annotation.AspectAnnotation;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootTest
@EnableAspectJAutoProxy(exposeProxy = true)
class RankRedisApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    @AspectAnnotation
    public void aopTest() {
        System.out.println("开始测试" + "caolp");
    }

}
