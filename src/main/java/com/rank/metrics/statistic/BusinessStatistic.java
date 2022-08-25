package com.rank.metrics.statistic;

/**
 * @author Caolp
 */
public interface BusinessStatistic {
    long getTotalSuccess();

    long getTotalFailCount();

    void clear();
}
