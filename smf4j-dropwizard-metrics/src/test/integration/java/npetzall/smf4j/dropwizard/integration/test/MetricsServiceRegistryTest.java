package npetzall.smf4j.dropwizard.integration.test;

import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.CoreOptions;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.karaf.options.KarafDistributionOption;
import org.ops4j.pax.exam.options.MavenArtifactUrlReference;
import org.ops4j.pax.exam.options.MavenUrlReference;

import java.io.File;

@RunWith(PaxExam.class)
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
                // KarafDistributionOption.debugConfiguration("5005", true),
                KarafDistributionOption.karafDistributionConfiguration()
                        .frameworkUrl(karafUrl)
                        .unpackDirectory(new File("target/exam"))
                        .useDeployFolder(false),
                KarafDistributionOption.keepRuntimeFolder(),
                KarafDistributionOption.features(karafStandardRepo , "scr"),
                CoreOptions.mavenBundle()
                        .groupId("org.ops4j.pax.exam.samples")
                        .artifactId("pax-exam-sample8-ds")
                        .versionAsInProject().start(),
        };
    }

}
