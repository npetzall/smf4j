package npetzall.smf4j.api.metrics;

public interface Meter {
    void happened();
    void happened(long numberOfTimes);
}
