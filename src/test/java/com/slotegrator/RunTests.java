package com.slotegrator;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty",
        "html:target/cucumber-html-report",
        "de.monochromata.cucumber.report.PrettyReports"},
        features = "src/test/java/com/slotegrator/features",
        glue = {"com.slotegrator.steps"})
public class RunTests {

}
