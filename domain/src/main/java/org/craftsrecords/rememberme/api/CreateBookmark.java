package org.craftsrecords.rememberme.api;

import org.craftsrecords.rememberme.bookmark.Bookmark;

import java.net.URL;

@FunctionalInterface
public interface CreateBookmark {
    Bookmark forResource(URL link);
}
