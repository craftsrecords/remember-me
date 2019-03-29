package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.BookmarkCreator;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.jupiter.api.*;

import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

class BookmarkCreatorTest {

    private static String url;
    private static String name;
    private static Set<String> tags;

    private CreateBookmark createBookmark;
    private Bookmarks bookmarks;

    @BeforeAll
    static void global_set_up() {
        url = "http://www.test.com";
        name = "Some name";
        tags = singleton("tag");
    }

    @BeforeEach
    void set_up() {
        bookmarks = new InMemoryBookmarks();
        createBookmark = new BookmarkCreator(bookmarks);
    }

    @AfterEach
    void tear_down() {
        bookmarks = null;
        createBookmark = null;
    }

    @Test
    @DisplayName("Should create the bookmark")
    void should_create_the_bookmark() {
        createBookmark.forResource(url, name, tags);

        Optional<Bookmark> saved = bookmarks.getBy(url);
        Bookmark expected = Bookmark.create(url, name, tags);

        assertThat(saved).hasValue(expected);
    }

    @Test
    @DisplayName("Should return the bookmark after creating it")
    void should_return_the_bookmark_after_creating_it() {
        Bookmark createdBookmark = createBookmark.forResource(url, name, tags);

        assertThat(createdBookmark).isNotNull();
    }

}