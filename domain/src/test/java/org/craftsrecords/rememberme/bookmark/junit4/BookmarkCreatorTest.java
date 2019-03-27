package org.craftsrecords.rememberme.bookmark.junit4;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.BookmarkCreator;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkCreatorTest {

    private CreateBookmark createBookmark;
    private Bookmarks bookmarks;

    @Before
    public void set_up() {
        bookmarks = new InMemoryBookmarks();
        createBookmark = new BookmarkCreator(bookmarks);
    }

    @Test
    public void should_create_the_bookmark() {
        String url = "http://www.test.com";
        String name = "Some name";
        Set<String> tags = singleton("tag");

        createBookmark.forResource(url, name, tags);

        Optional<Bookmark> saved = bookmarks.getBy(url);
        Bookmark expected = Bookmark.create(url, name, tags);

        assertThat(saved).hasValue(expected);
    }

    @Test
    public void should_return_the_bookmark_after_creating_it() {
        String url = "http://www.test.com";
        String name = "Some name";
        Set<String> tags = singleton("tag");

        Bookmark createdBookmark = createBookmark.forResource(url, name, tags);

        assertThat(createdBookmark).isNotNull();
    }

}