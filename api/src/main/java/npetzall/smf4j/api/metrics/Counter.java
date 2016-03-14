package npetzall.smf4j.api.metrics;

public interface Counter {
    void incremenent();
    void incremenent(long value);
    void decrement();
    void decrement(long value);
}
