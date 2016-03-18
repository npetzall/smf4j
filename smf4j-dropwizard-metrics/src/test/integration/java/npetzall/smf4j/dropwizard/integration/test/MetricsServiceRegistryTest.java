package npetzall.smf4j.dropwizard.integration.test;

import npetzall.smf4j.api.MetricsService;
import npetzall.smf4j.api.metrics.Counter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;

import javax.inject.Inject;
import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;
import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class MetricsServiceRegistryTest {

    @Configuration
    public Option[] config() {
        MavenArtifactUrlReference karafUrl = CoreOptions.maven()
                .groupId("org.apache.karaf")
                .artifactId("apache-karaf")
                .version("4.0.4")
                .type("tar.gz");
        MavenUrlReference karafStandardRepo = CoreOptions.maven()
                .groupId("org.apache.karaf.features")
                .artifactId("standard")
                .classifier("features")
                .type("xml")
                .version("4.0.4");
        return new Option[] {
                //KarafDistributionOption.debugConfiguration("5005", true),
                KarafDistributionOption.karafDistributionConfiguration()
                        .frameworkUrl(karafUrl)
                        .unpackDirectory(new File("build/exam"))
                        .useDeployFolder(false),
                KarafDistributionOption.keepRuntimeFolder(),
                KarafDistributionOption.features(karafStandardRepo , "scr"),
                mavenBundle("org.assertj","assertj-core","2.1.0"),
                mavenBundle()
                        .groupId("io.dropwizard.metrics")
                        .artifactId("metrics-core")
                        .version("3.1.2").start(),
                mavenBundle()
                        .groupId("npetzall.smf4j")
                        .artifactId("smf4j-api")
                        .version("0.0.1").start(),
                mavenBundle()
                        .groupId("npetzall.smf4j")
                        .artifactId("smf4j-dropwizard-metrics")
                        .version("0.0.1").start(),
                junitBundles()
        };
    }

    @Inject
    MetricsService metricsService;

    @Test
    public void canIncremenentCounter() {
        Counter counter =  metricsService.get().getCounter(this.getClass(), "testCounter");
        counter.incremenent();
        counter.incremenent(5l);
        counter.decrement(2l);
        assertThat(counter.getCount()).isEqualTo(4);
    }

}
