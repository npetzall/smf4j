package npetzall.smf4j.dropwizard;

import com.codahale.metrics.MetricRegistry;
import npetzall.smf4j.api.metrics.Counter;
import npetzall.smf4j.api.metrics.Histogram;
import npetzall.smf4j.api.metrics.Meter;
import npetzall.smf4j.api.metrics.Timer;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MetricsServiceImplTest {

    private MetricsServiceImpl metricsService = new MetricsServiceImpl();

    @Test
    public void timerTest() {
        Timer timer = metricsService.get().getTimer(MetricsServiceImpl.class, "timerTest");
        Timer.ActiveTimer activeTimer = timer.time();
        for(int i = 0; i<=10; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        activeTimer.stop();
        MetricRegistry metricRegistry = metricsService.get().getMetricRegistry();
        com.codahale.metrics.Timer realTimer = metricRegistry.getTimers().get(MetricRegistry.name(MetricsServiceImpl.class, "timerTest"));
        assertThat(realTimer.getCount()).isEqualTo(1);
        assertThat(realTimer.getSnapshot().getMin()).isBetween(10000000000l,12000000000l);
    }

    @Test
    public void meterTest() {
        Meter meter = metricsService.get().getMeter(MetricsServiceImpl.class, "meterTest");
        meter.happened();
        com.codahale.metrics.Meter realMeter = metricsService.get().getMetricRegistry().getMeters().get(MetricRegistry.name(MetricsServiceImpl.class, "meterTest"));
        meter.happened();
        assertThat(realMeter.getCount()).isEqualTo(2);
    }

    @Test
    public void histogramTest() {
        Histogram histogram = metricsService.get().getHistogram(MetricsServiceImpl.class, "histogramTest");
        histogram.update(1000);
        com.codahale.metrics.Histogram realHistogram = metricsService.get().getMetricRegistry().getHistograms().get(MetricRegistry.name(MetricsServiceImpl.class, "histogramTest"));
        assertThat(realHistogram.getCount()).isEqualTo(1);
        assertThat(realHistogram.getSnapshot().getMin()).isEqualTo(1000);
    }

    @Test
    public void counterTest() {
        Counter counter = metricsService.get().getCounter(MetricsServiceImpl.class, "counterTest");
        counter.incremenent();
        counter.incremenent();
        counter.incremenent(2);
        counter.decrement();
        MetricRegistry metricRegistry = metricsService.get().getMetricRegistry();
        com.codahale.metrics.Counter realCounter = metricRegistry.getCounters().get(MetricRegistry.name(MetricsServiceImpl.class,"counterTest"));
        assertThat(realCounter.getCount()).isEqualTo(3);
    }

    @Test
    public void counterTwoRegistries() {
        Counter counterOne = metricsService.get().getCounter(MetricsServiceImpl.class, "counterTwoOne");
        Counter counterTwo = metricsService.get().getCounter(MetricsServiceImpl.class, "counterTwoTwo");

        counterOne.incremenent();
        counterTwo.incremenent(2);

        MetricRegistry metricRegistry = metricsService.get().getMetricRegistry();

        com.codahale.metrics.Counter realCounterOne = metricRegistry.getCounters().get(MetricRegistry.name(MetricsServiceImpl.class,"counterTwoOne"));
        com.codahale.metrics.Counter realCounterTwo = metricRegistry.getCounters().get(MetricRegistry.name(MetricsServiceImpl.class,"counterTwoTwo"));

        assertThat(realCounterOne.getCount()).isEqualTo(1);
        assertThat(realCounterTwo.getCount()).isEqualTo(2);
    }
}
