package com.rank.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rank.cache.component.CacheConfigReader;
import com.rank.dto.CanalJson;


/**
 * @author Caolp
 */

public class JsonUtil<T> {


    public static CanalJson parseObject(String json){

        JSONObject jsonObject = JSONObject.parseObject(json);

        return jsonObject.toJavaObject(CanalJson.class);


    }

    public static void main(String[] args) {
        String json ="{\"data\":[{\"id\":\"4\",\"name\":\"marry\",\"score\":\"76\",\"create_id\":\"2\",\"create_instant\":\"2021-09-07 11:12:15\",\"modify_id\":\"1\",\"modify_instant\":\"2021-09-07 11:12:15\"}],\"database\":\"ranking_redis\",\"es\":1631152184000,\"id\":6,\"isDdl\":false,\"mysqlType\":{\"id\":\"int\",\"name\":\"varchar(200)\",\"score\":\"int\",\"create_id\":\"int\",\"create_instant\":\"timestamp\",\"modify_id\":\"int\",\"modify_instant\":\"timestamp\"},\"old\":[{\"score\":\"90\",\"create_id\":\"1\"}],\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":4,\"name\":12,\"score\":4,\"create_id\":4,\"create_instant\":93,\"modify_id\":4,\"modify_instant\":93},\"table\":\"user\",\"ts\":1631152184865,\"type\":\"UPDATE\"}\n";
        CanalJson canalJson = JsonUtil.parseObject(json);
        System.out.println(canalJson.getData().get(0).get("score"));
        System.out.println(canalJson);

    }

}
