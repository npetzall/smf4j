package npetzall.smf4j.dropwizard.metrics;

import com.codahale.metrics.Counter;

public class CounterDelegate implements npetzall.smf4j.api.metrics.Counter {

    private final Counter counter;

    public CounterDelegate(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void incremenent() {
        counter.inc();
    }

    @Override
    public void incremenent(long value) {
        counter.inc(value);
    }

    @Override
    public void decrement() {
        counter.dec();
    }

    @Override
    public void decrement(long value) {
        counter.dec(value);
    }

    @Override
    public long getCount() {
        return counter.getCount();
    }
}
