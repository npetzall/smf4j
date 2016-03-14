package npetzall.smf4j.dropwizard.metrics;

import com.codahale.metrics.Timer;

public class TimerDelegate implements npetzall.smf4j.api.metrics.Timer {

    private final Timer timer;

    public TimerDelegate(Timer timer) {
        this.timer = timer;
    }

    @Override
    public ActiveTimer time() {
        return new ContextDelegate(timer.time());
    }

    class ContextDelegate implements ActiveTimer {

        private final Timer.Context context;

        public ContextDelegate(Timer.Context context) {
            this.context = context;
        }

        @Override
        public void stop() {
            context.stop();
        }
    }
}
