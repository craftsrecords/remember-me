package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.FindBookmarks;

import java.util.Collection;
import java.util.stream.Collectors;

public class BookmarksFinder implements FindBookmarks {

    private Bookmarks bookmarks;

    public BookmarksFinder(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public Collection<Bookmark> by(String tag) {
        return bookmarks.getAll().stream()
                .filter(bookmark -> bookmark.hasTag(tag))
                .collect(Collectors.toSet());
    }

}
