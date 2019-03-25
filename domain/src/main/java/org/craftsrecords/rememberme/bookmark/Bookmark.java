package org.craftsrecords.rememberme.bookmark;

import java.net.URL;
import java.util.Objects;

public class Bookmark {

    private URL url;
    private String name;
    private Tags tags;

    public Bookmark(URL url, String name) {
        this(url, name, Tags.empty());
    }

    public Bookmark(URL url, String name, Tags tags) {
        this.url = url;
        this.name = name;
        this.tags = tags;
    }

    public URL getUrl() {
        return url;
    }

    public String getName() {
        return name;
    }

    public Tags getTags() {
        return tags;
    }

    public boolean hasTag(String tag) {
        return tags.contains(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Bookmark bookmark = (Bookmark) o;
        return Objects.equals(url, bookmark.url)
                && Objects.equals(name, bookmark.name)
                && Objects.equals(tags, bookmark.tags);
    }

    @Override
    public int hashCode() {
        int result = url != null ? url.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
