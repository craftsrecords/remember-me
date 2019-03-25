package org.craftsrecords.rememberme.rest;

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

}
