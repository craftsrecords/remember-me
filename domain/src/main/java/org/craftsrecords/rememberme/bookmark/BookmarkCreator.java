package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;

import java.util.Collection;

public class BookmarkCreator implements CreateBookmark {

    private Bookmarks bookmarks;

    public BookmarkCreator(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public Bookmark forResource(String url, String name, Collection<String> tags) {
        Bookmark bookmark = Bookmark.create(url, name, tags);
        return bookmarks.save(bookmark);
    }

}
