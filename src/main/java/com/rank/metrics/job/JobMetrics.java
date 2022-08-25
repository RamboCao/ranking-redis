package com.rank.metrics.job;

import com.rank.metrics.statistic.JobStatistic;
import io.micrometer.core.instrument.FunctionCounter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;

import java.util.function.ToDoubleFunction;

/**
 * @author Caolp
 */
public class JobMetrics implements MeterBinder {

    JobStatistic statistic;
    private final Iterable<Tag> tags;

    public JobMetrics(JobStatistic jobStatistic, Iterable<Tag> tags) {
        this.tags = Tags.concat(tags, "metricClass", "JobMetrics");
        this.statistic = jobStatistic;
    }

    @Override
    public void bindTo(MeterRegistry registry) {
        counter(registry, "job.task.success.total", "Job Task Total Run Success", JobStatistic::getTotalSuccess);
    }

    private void counter(MeterRegistry registry, String name, String description,
                         ToDoubleFunction<JobStatistic> func, String... extraTags){
        FunctionCounter.builder(name, statistic, func)
                .tags(tags)
                .tags(extraTags)
                .description(description)
                .register(registry);
    }
}
