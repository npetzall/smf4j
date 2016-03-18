package npetzall.smf4j.api;

import npetzall.smf4j.api.metrics.Counter;
import npetzall.smf4j.api.metrics.Histogram;
import npetzall.smf4j.api.metrics.Meter;
import npetzall.smf4j.api.metrics.Timer;

public interface MetricsRegistry {

    Counter getCounter(Class clazz, String...name);
    Histogram getHistogram(Class clazz, String...name);
    Meter getMeter(Class clazz, String...name);
    Timer getTimer(Class clazz, String...name);

}
