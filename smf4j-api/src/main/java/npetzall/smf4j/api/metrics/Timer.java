package npetzall.smf4j.api.metrics;

public interface Timer {

    ActiveTimer time();
    long getCount();

    interface ActiveTimer {
        void stop();
    }
}
