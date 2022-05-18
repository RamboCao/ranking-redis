package com.rank.cache.config;

import lombok.ToString;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Caolp
 */
@XmlRootElement
@ToString
public class SqlConfig {

    public static final String STAR = "*";
    public static final String TABLE_ALIAS = "R";
    public static final String POINT = ".";
    public static final String DOT = ",";

    private String key;

    private String table;

    private List<Column> columns;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    @XmlElementWrapper(name = "columns")
    @XmlElement(name = "column")
    public List<Column> getColumns() {
        return columns;
    }

    public void setColumns(List<Column> columns) {
        this.columns = columns;
    }
}
