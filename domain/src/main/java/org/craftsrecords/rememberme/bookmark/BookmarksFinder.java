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
    public Collection<Bookmark> by(Collection<String> tags) {
        return bookmarks.getAll().stream()
                .filter(bookmark -> tags.stream().anyMatch(bookmark::hasTag))
                .collect(Collectors.toSet());
    }

}
