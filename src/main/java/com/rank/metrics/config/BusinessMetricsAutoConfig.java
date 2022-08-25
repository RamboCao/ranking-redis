package com.rank.metrics.config;

import com.rank.metrics.job.JobMetrics;
import com.rank.metrics.statistic.JobStatistic;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManagerFactory;

/**
 * @author Caolp
 */
@Configuration
@AutoConfigureAfter({MetricsAutoConfiguration.class, SimpleMetricsExportAutoConfiguration.class})
@ConditionalOnClass({ EntityManagerFactory.class, MeterRegistry.class})
@ConditionalOnBean({ EntityManagerFactory.class, MeterRegistry.class})
public class BusinessMetricsAutoConfig {

    @Bean
    @ConditionalOnMissingBean
    public MeterBinder jobMetrics() {

        return new JobMetrics(JobStatistic.get(), Tags.empty());

    }

}
