package org.craftsrecords.rememberme.api;

import org.craftsrecords.rememberme.bookmark.Bookmark;

import java.net.URL;
import java.util.Collection;

@FunctionalInterface
public interface CreateBookmark {
    Bookmark forResource(URL url, String name, Collection<String> tags);
}
