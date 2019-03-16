package org.craftsrecords.rememberme.bookmark;

import java.net.URL;
import java.util.Objects;

public class Bookmark {

    private URL url;
    private Tags tags;

    public Bookmark(URL url) {
        this(url, Tags.empty());
    }

    public Bookmark(URL url, Tags tags) {
        this.url = url;
        this.tags = tags;
    }

    public URL getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(url, bookmark.url)
                && Objects.equals(tags, bookmark.tags);
    }

    @Override
    public int hashCode() {
        return (31 * url.hashCode()) + tags.hashCode();
    }
}
