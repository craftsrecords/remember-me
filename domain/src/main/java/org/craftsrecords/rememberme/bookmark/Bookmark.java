package org.craftsrecords.rememberme.bookmark;

import java.net.URL;

public class Bookmark {

    private URL url;

    Bookmark(URL url) {
        this.url = url;
    }

    URL getUrl() {
        return url;
    }
}
