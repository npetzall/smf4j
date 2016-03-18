package npetzall.smf4j.dropwizard.integration.test;

import npetzall.smf4j.api.MetricsService;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.testng.listener.PaxExam;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.io.File;

import static org.ops4j.pax.exam.CoreOptions.mavenBundle;

@Listeners(PaxExam.class)
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
                KarafDistributionOption.debugConfiguration("5005", true),
                KarafDistributionOption.karafDistributionConfiguration()
                        .frameworkUrl(karafUrl)
                        .unpackDirectory(new File("build/exam"))
                        .useDeployFolder(false),
                KarafDistributionOption.keepRuntimeFolder(),
                KarafDistributionOption.features(karafStandardRepo , "scr"),
                mavenBundle()
                        .groupId("io.dropwizard.metrics")
                        .artifactId("metrics-core")
                        .version("3.1.2").start(),
                /*mavenBundle()
                        .groupId("javax.inject")
                        .artifactId("javax.inject")
                        .version("1").start(),
                mavenBundle()
                        .groupId("org.ops4j.pax.exam")
                        .artifactId("pax-exam-testng")
                        .version("4.7.0").start(),*/
                mavenBundle()
                        .groupId("org.testng")
                        .artifactId("testng")
                        .version("6.9.10").start(),
                mavenBundle()
                        .groupId("npetzall.smf4j")
                        .artifactId("smf4j-api")
                        .version("0.0.1").start(),
                mavenBundle()
                        .groupId("npetzall.smf4j")
                        .artifactId("smf4j-dropwizard-metrics")
                        .version("0.0.1").start()
        };
    }

    @Inject
    MetricsService metricsService;

    @Test
    public void canIncremenentCounter() {
        metricsService.get().getCounter(this.getClass(), "testCounter").incremenent();
    }

    /*@Test(dependsOnMethods = "canIncremenentCounter")
    public void canIncremenentCounterByTwo() {
        metricsService.get().getCounter(this.getClass(), "testCounter").incremenent(2);
    }

    @Test(dependsOnMethods = "canIncremenentCounterByTwo")
    public void verifyCountIsThree() {
        long count = metricsService.get().getCounter(this.getClass(), "testCounter").getCount();
        assertThat(count).isEqualTo(3);
    }*/

}
