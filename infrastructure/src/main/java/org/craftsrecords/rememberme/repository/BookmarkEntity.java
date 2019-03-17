package org.craftsrecords.rememberme.repository;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Tags;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.net.URL;
import java.util.Set;

@Entity
public class BookmarkEntity {

    @Id
    private URL url;

    private String name;

    @ElementCollection
    private Set<String> tags;

    public BookmarkEntity() {
    }

    private BookmarkEntity(URL url, String name, Set<String> tags) {
        this.url = url;
        this.name = name;
        this.tags = tags;
    }

    static BookmarkEntity from(Bookmark bookmark) {
        return new BookmarkEntity(
                bookmark.getUrl(),
                bookmark.getName(),
                bookmark.getTags().toSet()
        );
    }

    Bookmark toValueObject() {
        return new Bookmark(url, name, new Tags(tags));
    }

}
