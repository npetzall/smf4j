package npetzall.smf4j.api.metrics;

public interface Timer {
    ActiveTimer time();

    interface ActiveTimer {
        void stop();
    }
}
