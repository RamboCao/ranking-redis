package com.rank.common.page;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Caolp
 */
@ToString
public class PageCondition implements Serializable {

    private static final Integer PAGE_DEFAULT_VALUE =-1;

    private static final Integer LIMIT_DEFAULT_VALUE = Integer.MAX_VALUE;

    /**
     * 页数
     */
    private Integer page;

    /**
     * 查询条数
     */
    private Integer limit;

    /**
     * 计数
     */
    private Boolean count;



}
