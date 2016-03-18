package npetzall.smf4j.dropwizard.metrics;

import com.codahale.metrics.Histogram;

public class HistogramDelegate implements npetzall.smf4j.api.metrics.Histogram {

    private final Histogram histogram;

    public HistogramDelegate(Histogram histogram) {
        this.histogram = histogram;
    }

    @Override
    public void update(long value) {
        histogram.update(value);
    }

    @Override
    public long getCount() {
        return histogram.getCount();
    }
}
