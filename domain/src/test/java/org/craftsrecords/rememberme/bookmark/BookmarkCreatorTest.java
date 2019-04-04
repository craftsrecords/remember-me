package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Optional;
import java.util.Set;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkCreatorTest {

    private static String url;
    private static String name;
    private static Set<String> tags;

    private CreateBookmark createBookmark;
    private Bookmarks bookmarks;

    @BeforeClass
    public static void global_set_up() {
        url = "http://www.test.com";
        name = "Some name";
        tags = singleton("tag");
    }

    @Before
    public void set_up() {
        bookmarks = new InMemoryBookmarks();
        createBookmark = new BookmarkCreator(bookmarks);
    }

    @After
    public void tear_down() {
        bookmarks = null;
        createBookmark = null;
    }

    @Test
    @Ignore("Not implemented yet")
    public void should_create_the_bookmark() {
        createBookmark.forResource(url, name, tags);

        Optional<Bookmark> saved = bookmarks.getBy(url);

        Bookmark expected = Bookmark.create(url, name, tags);
        assertThat(saved).hasValue(expected);
    }

    @Test
    public void should_return_the_bookmark_after_creating_it() {
        Bookmark createdBookmark = createBookmark.forResource(url, name, tags);

        Assert.assertNotNull(createdBookmark);
    }

}