package npetzall.smf4j.dropwizard;

import com.codahale.metrics.MetricRegistry;
import npetzall.smf4j.api.MetricsService;

import java.util.concurrent.ConcurrentHashMap;

public class MetricsServiceImpl implements MetricsService {

    private final MetricsRegistryDelegate metricsRegistry = new MetricsRegistryDelegate(new MetricRegistry());

    private final ConcurrentHashMap<String, MetricsRegistryDelegate> metricsRegistryMap = new ConcurrentHashMap<>();

    @Override
    public MetricsRegistryDelegate get() {
        return metricsRegistry;
    }

    @Override
    public MetricsRegistryDelegate get(String registryName) {
        MetricsRegistryDelegate metricsRegistry = metricsRegistryMap.get(registryName);
        if (metricsRegistry == null) {
            metricsRegistry = createMetricsRegistry(registryName);
        }
        return metricsRegistry;
    }

    private synchronized MetricsRegistryDelegate createMetricsRegistry(String registryName) {
        MetricsRegistryDelegate metricsRegistry = metricsRegistryMap.get(registryName);
        if (metricsRegistry == null) {
            metricsRegistry = new MetricsRegistryDelegate(new MetricRegistry());
            metricsRegistryMap.put(registryName, metricsRegistry);
        }
        return metricsRegistry;
    }
}
