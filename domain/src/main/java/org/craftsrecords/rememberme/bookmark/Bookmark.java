package org.craftsrecords.rememberme.bookmark;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class Bookmark {

    private URL url;
    private String name;
    private Tags tags;

    private Bookmark(URL url, String name, Tags tags) {
        this.url = url;
        this.name = name;
        this.tags = tags;
    }

    public static Bookmark create(String url, String name, Collection<String> tags) {
        checkIfEmpty(name);
        URL validUrl = checkIfValid(url);
        return new Bookmark(validUrl, name, Tags.of(tags));
    }

    private static void checkIfEmpty(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid name: it should not be empty");
        }
    }

    private static URL checkIfValid(String url) {
        try {
            return new URL(url);
        } catch (MalformedURLException e) {
            throw new IllegalArgumentException("Invalid url: " + e.getMessage());
        }
    }

    public String getUrl() {
        return url.toString();
    }

    public String getName() {
        return name;
    }

    public Set<String> getTags() {
        return tags.toSet();
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

    @Override
    public String toString() {
        return "Bookmark{" +
                "url=" + url +
                ", name='" + name + '\'' +
                ", tags=" + tags +
                '}';
    }
}
