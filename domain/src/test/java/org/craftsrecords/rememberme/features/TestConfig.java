package org.craftsrecords.rememberme.features;

import cucumber.runtime.java.picocontainer.PicoFactory;
import org.craftsrecords.rememberme.features.stepdefs.TestContext;

public class TestConfig extends PicoFactory {

    public TestConfig() {
        addClass(TestContext.class);
    }
}
