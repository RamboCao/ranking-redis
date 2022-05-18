package com.rank.cache.config;

import lombok.ToString;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Caolp
 */
@XmlRootElement
@ToString
public class Column {

    private String filed;

    public String getFiled() {
        return filed;
    }

    public void setFiled(String filed) {
        this.filed = filed;
    }
}
