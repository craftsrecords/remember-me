package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;

import java.net.URL;
import java.util.Collection;

public class BookmarkCreator implements CreateBookmark {

    private Bookmarks bookmarks;

    public BookmarkCreator(Bookmarks bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public Bookmark forResource(URL url, String name, Collection<String> tags) {
        Bookmark bookmark = new Bookmark(url, name, new Tags(tags));
        return bookmarks.save(bookmark);
    }

}
