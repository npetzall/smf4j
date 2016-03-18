package npetzall.smf4j.api;

public interface MetricsService {

    MetricsRegistry get();
    MetricsRegistry get(String registryName);

}
