package npetzall.smf4j.dropwizard;

import com.codahale.metrics.MetricRegistry;
import npetzall.smf4j.api.MetricsRegistry;
import npetzall.smf4j.api.metrics.Counter;
import npetzall.smf4j.api.metrics.Histogram;
import npetzall.smf4j.api.metrics.Meter;
import npetzall.smf4j.api.metrics.Timer;
import npetzall.smf4j.dropwizard.metrics.CounterDelegate;
import npetzall.smf4j.dropwizard.metrics.HistogramDelegate;
import npetzall.smf4j.dropwizard.metrics.MeterDelegate;
import npetzall.smf4j.dropwizard.metrics.TimerDelegate;

public class MetricsRegistryDelegate implements MetricsRegistry {

    private final MetricRegistry metricRegistry;

    public MetricsRegistryDelegate(MetricRegistry metricRegistry) {
        this.metricRegistry = metricRegistry;
    }

    public MetricRegistry getMetricRegistry() {
        return metricRegistry;
    }

    @Override
    public Counter getCounter(Class clazz, String... qualifiers) {
        return new CounterDelegate(metricRegistry.counter(name(clazz, qualifiers)));
    }

    @Override
    public Histogram getHistogram(Class clazz, String... qualifiers) {
        return new HistogramDelegate(metricRegistry.histogram(name(clazz, qualifiers)));
    }

    @Override
    public Meter getMeter(Class clazz, String... qualifiers) {
        return new MeterDelegate(metricRegistry.meter(name(clazz, qualifiers)));
    }

    @Override
    public Timer getTimer(Class clazz, String... qualifiers) {
        return new TimerDelegate(metricRegistry.timer(name(clazz, qualifiers)));
    }

    private static String name(Class clazz, String...qualifier) {
        return MetricRegistry.name(clazz,qualifier);
    }

}
