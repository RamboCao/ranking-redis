package com.rank.dto;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Caolp
 */
@Data
@ToString
public class CanalJson {

    private List<Map<String, Object>> data;

    private List<String> pkNames;

    private List<Map<String, Object>> old;

    private String type;

    private Long es;

    private String sql;

    private String database;

    private List<Map<String, Object>> sqlType;

    private List<Map<String, Object>> mysqlType;

    private Long id;

    private Boolean isDdl;

    private String table;

    private Long ts;

}
