package npetzall.smf4j.dropwizard.metrics;

import com.codahale.metrics.Meter;

public class MeterDelegate implements npetzall.smf4j.api.metrics.Meter {

    private final Meter meter;

    public MeterDelegate(Meter meter){
        this.meter = meter;
    }

    @Override
    public void happened() {
        meter.mark();
    }

    @Override
    public void happened(long numberOfTimes) {
        meter.mark(numberOfTimes);
    }

    @Override
    public long getCount() {
        return meter.getCount();
    }
}
