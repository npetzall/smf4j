package npetzall.smf4j.api.metrics;

public interface Histogram {
    void update(long value);
}
