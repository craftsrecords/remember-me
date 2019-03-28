package org.craftsrecords.rememberme.repository;

import org.craftsrecords.rememberme.bookmark.Bookmark;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import java.util.Set;

@Entity
public class BookmarkEntity {

    @Id
    private String url;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private Set<String> tags;

    public BookmarkEntity() {
    }

    private BookmarkEntity(String url, String name, Set<String> tags) {
        this.url = url;
        this.name = name;
        this.tags = tags;
    }

    public static BookmarkEntity from(Bookmark bookmark) {
        return new BookmarkEntity(
                bookmark.getUrl(),
                bookmark.getName(),
                bookmark.getTags()
        );
    }

    Bookmark toValueObject() {
        return Bookmark.create(url, name, tags);
    }

}
