package com.rank.cache.config;

import lombok.ToString;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Caolp
 */
@XmlRootElement
@ToString
public class CacheConfig {

    private String moduleName;

    private String dbUsername;


    private List<SqlConfig> sqlConfigs;

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getDbUsername() {
        return dbUsername;
    }

    public void setDbUsername(String dbUsername) {
        this.dbUsername = dbUsername;
    }

    public void setSqlConfigs(List<SqlConfig> sqlConfigs) {
        this.sqlConfigs = sqlConfigs;
    }


    @XmlElementWrapper(name = "sqlConfigs")
    @XmlElement(name = "sqlConfig")
    public List<SqlConfig> getSqlConfigs() {
        return sqlConfigs;
    }
}
