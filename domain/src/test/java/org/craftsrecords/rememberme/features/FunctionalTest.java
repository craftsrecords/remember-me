package org.craftsrecords.rememberme.features;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        strict = true,
        plugin = {"pretty"},
        features = {"classpath:features"})
public class FunctionalTest {
}
