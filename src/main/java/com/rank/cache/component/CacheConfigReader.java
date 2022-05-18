package com.rank.cache.component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rank.cache.config.CacheConfig;
import com.rank.cache.config.Column;
import com.rank.cache.config.SqlConfig;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.StringUtils;
import org.xml.sax.InputSource;

import javax.swing.*;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author Caolp
 */
@Slf4j
@NoArgsConstructor
@ToString
public class CacheConfigReader {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheConfigReader.class);

    private static final String CACHE_CONFIG_XML_PATH = "cache-config";


    public static List<CacheConfig> getAllSqlConfigs(){

        List<CacheConfig> cacheConfigs = new ArrayList<>();
        List<InputSource> xmlConfigSources = getXmlConfigFiles(CACHE_CONFIG_XML_PATH);
        try {
            JAXBContext context = JAXBContext.newInstance(CacheConfig.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            for (InputSource xmlConfigSource : xmlConfigSources) {
                cacheConfigs.add((CacheConfig) unmarshaller.unmarshal(xmlConfigSource));
            }
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return cacheConfigs;
    }

    private static List<InputSource> getXmlConfigFiles(String path) {
        List<InputSource> result = new ArrayList<>();
        try {
            ClassPathResource classPathResource = new ClassPathResource(path);
            if (classPathResource.exists()) {
                File[] files = classPathResource.getFile().listFiles();
                if (files != null) {
                    for (File file : files) {
                        if (file.getName().endsWith("-cache.xml")) {
                            result.add(new InputSource(new FileInputStream(file)));
                        }
                    }
                }
            } else {
                LOGGER.warn("file [" + path + "] not exists");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static Map<String, List<String>> configTableColumns(){
        Map<String, List<String>> result = new HashMap<>(16);
        CacheConfigReader.getAllSqlConfigs().forEach(cacheConfig -> {
            cacheConfig.getSqlConfigs().forEach(sqlConfig -> {
                String tableName = sqlConfig.getTable();
                List<String> columns = new ArrayList<>();
                sqlConfig.getColumns().forEach(column -> {
                    columns.add(column.getFiled());
                });
                result.put(tableName, columns);
            });
        });

        return result;
    }

    public static void main(String[] args) {
        System.out.println(CacheConfigReader.getAllSqlConfigs());

        System.out.println(CacheConfigReader.configTableColumns());


    }

}
