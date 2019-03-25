package org.craftsrecords.rememberme.features;

import cucumber.runtime.java.picocontainer.PicoFactory;
import org.craftsrecords.rememberme.bookmark.BookmarkCreator;
import org.craftsrecords.rememberme.bookmark.BookmarksFinder;
import org.craftsrecords.rememberme.features.stepdefs.TestContext;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;

public class TestConfig extends PicoFactory {

    public TestConfig() {
        addClass(TestContext.class);
        addClass(BookmarkCreator.class);
        addClass(InMemoryBookmarks.class);
        addClass(BookmarksFinder.class);
    }
}
