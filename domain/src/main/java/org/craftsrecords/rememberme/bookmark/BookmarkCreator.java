package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;

import java.net.URL;

public class BookmarkCreator implements CreateBookmark {

    private Bookmarks bookmarks;

    public BookmarkCreator(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public Bookmark forResource(URL url) {
        Bookmark bookmark = new Bookmark(url);
        return bookmarks.save(bookmark);
    }

}
