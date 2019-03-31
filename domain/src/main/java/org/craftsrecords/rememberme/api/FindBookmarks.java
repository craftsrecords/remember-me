package org.craftsrecords.rememberme.api;

import org.craftsrecords.rememberme.bookmark.Bookmark;

import java.util.Collection;

@FunctionalInterface
public interface FindBookmarks {
    Collection<Bookmark> by(String tags);
}
