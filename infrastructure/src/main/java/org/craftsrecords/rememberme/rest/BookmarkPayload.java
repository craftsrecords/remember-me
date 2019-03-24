package org.craftsrecords.rememberme.rest;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Tags;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;

class BookmarkPayload {

    public String url;
    public String name;
    public Collection<String> tags;

    BookmarkPayload() {
    }

    BookmarkPayload(String url, String name, Collection<String> tags) {
        this.url = url;
        this.name = name;
        this.tags = tags;
    }

    Bookmark toBookmark() throws MalformedURLException {
        return new Bookmark(new URL(url), name, new Tags(tags));
    }

}
