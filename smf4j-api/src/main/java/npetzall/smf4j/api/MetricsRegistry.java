package npetzall.smf4j.api;

import npetzall.smf4j.api.metrics.Counter;
import npetzall.smf4j.api.metrics.Histogram;
import npetzall.smf4j.api.metrics.Meter;
import npetzall.smf4j.api.metrics.Timer;

public interface MetricsRegistry {

    Counter getCounter(Class clazz, String...qualifiers);

    Histogram getHistogram(Class clazz, String...qualifiers);

    Meter getMeter(Class clazz, String...qualifiers);

    Timer getTimer(Class clazz, String...qualifiers);

}
