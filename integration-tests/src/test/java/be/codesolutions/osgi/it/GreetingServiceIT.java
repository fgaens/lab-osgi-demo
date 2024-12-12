package be.codesolutions.osgi.it;

import be.codesolutions.osgi.api.GreetingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.osgi.framework.BundleContext;

import javax.inject.Inject;
import java.io.File;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.ops4j.pax.exam.CoreOptions.*;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.features;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;

@RunWith(PaxExam.class)
public class GreetingServiceIT {

    @Inject
    private BundleContext bundleContext;

    @Inject
    private GreetingService greetingService;

    @Configuration
    public Option[] config() {
        return options(
                // Karaf container setup
                karafDistributionConfiguration()
                        .frameworkUrl(maven()
                                .groupId("org.apache.karaf")
                                .artifactId("apache-karaf")
                                .type("zip")
                                .version("4.4.0"))
                        .unpackDirectory(new File("target/exam")),

                // Enable SCR (needed for @Component annotations)
                features(maven()
                                .groupId("org.apache.karaf.features")
                                .artifactId("standard")
                                .type("xml")
                                .classifier("features")
                                .version("4.4.0"),
                        "scr"),

                // Install our bundles
                mavenBundle().groupId("be.codesolutions").artifactId("api").versionAsInProject(),
                mavenBundle().groupId("be.codesolutions").artifactId("provider").versionAsInProject()
        );
    }

    @Test
    public void testGreetingService() {
        assertNotNull("BundleContext should be initialized", bundleContext);
        assertNotNull("GreetingService should be available", greetingService);

        String greeting = greetingService.greet("Integration Test");
        assertTrue("Greeting should contain the name", greeting.contains("Integration Test"));
    }
}