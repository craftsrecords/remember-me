package org.craftsrecords.rememberme.features.stepdefs;

import org.craftsrecords.rememberme.bookmark.Bookmark;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class TestContext {
    String link;
    String name;
    boolean alreadyBookmarked;
    Set<String> tags = new HashSet<>();
    String searchedTag;
    Collection<Bookmark> searchResults;
}
