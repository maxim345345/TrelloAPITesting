package org.max;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = "org.max.cucumberTrello",
        plugin = {"pretty", "html:target/cucumber-report.html"},
        features = "src/test/resources/org/max/cucumberTrello/",
        publish = true
)
public class RunCucumberTest {
}
