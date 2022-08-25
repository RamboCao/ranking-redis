package com.rank.metrics;

import com.google.common.cache.LoadingCache;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.binder.cache.GuavaCacheMetrics;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Caolp
 */
@Slf4j
@Component
public class GuavaMetricRegistrar implements SmartInitializingSingleton {

    private static final Map<String, MeterBinder> meterBinders = new ConcurrentHashMap<>();

    @Resource
    MeterRegistry meterRegistry;

    public static <K, V> LoadingCache<K, V> registry(String name, LoadingCache<K, V> loadingCache) {
        meterBinders.putIfAbsent(name, new GuavaCacheMetrics<>(loadingCache, name, Tags.empty()));
        return loadingCache;
    }

    @Override
    public void afterSingletonsInstantiated() {
        log.info("metric registry cache names {}", meterBinders.keySet());
        meterBinders.values().forEach(mb -> mb.bindTo(meterRegistry));
    }
}
