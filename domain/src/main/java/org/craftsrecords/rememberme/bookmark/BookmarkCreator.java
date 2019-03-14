package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;

import java.net.URL;

public class BookmarkCreator implements CreateBookmark {

    @Override
    public Bookmark forResource(URL url) {
        return new Bookmark(url);
    }

}
