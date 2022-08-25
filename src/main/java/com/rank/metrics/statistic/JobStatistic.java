package com.rank.metrics.statistic;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author Caolp
 */
public class JobStatistic implements BusinessStatistic{

    private static class SingletonHolder{
        public static final JobStatistic INSTANCE = new JobStatistic();
    }

    public static JobStatistic get(){
        return SingletonHolder.INSTANCE;
    }

    private final LongAdder totalSuccessCount = new LongAdder();
    private final LongAdder totalFailCount = new LongAdder();

    @Override
    public long getTotalSuccess() {
        return totalSuccessCount.sum();
    }

    @Override
    public long getTotalFailCount() {
        return totalFailCount.sum();
    }

    @Override
    public void clear() {
        totalFailCount.reset();
        totalSuccessCount.reset();
    }
}
