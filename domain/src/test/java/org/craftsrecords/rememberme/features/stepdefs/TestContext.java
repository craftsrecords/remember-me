package org.craftsrecords.rememberme.features.stepdefs;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class TestContext {
    URL link;
    boolean alreadyBookmarked;
    String name;
    Set<String> tags = new HashSet<>();
}
