package org.craftsrecords.rememberme.stubs;

import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryBookmarks implements Bookmarks {

    private final Map<String, Bookmark> bookmarks = new HashMap<>();

    @Override
    public Bookmark save(Bookmark bookmark) {
        String url = bookmark.getUrl();
        if (bookmarks.containsKey(url)) {
            throw new AlreadyBookmarkedException(url);
        }
        bookmarks.put(url, bookmark);
        return bookmark;
    }

    @Override
    public Optional<Bookmark> getBy(String url) {
        Bookmark bookmark = bookmarks.get(url);
        return Optional.ofNullable(bookmark);
    }

    @Override
    public Collection<Bookmark> getAll() {
        return bookmarks.values();
    }

}
