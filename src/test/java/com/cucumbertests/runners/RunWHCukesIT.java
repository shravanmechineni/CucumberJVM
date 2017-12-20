package com.cucumbertests.runners;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        tags = "@EPL",
        glue = "com.cucumbertests.stepdefs.epl",
        plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"})
public class RunWHCukesIT {
}
