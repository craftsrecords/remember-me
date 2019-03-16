package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.Before;
import org.junit.Test;

import java.net.URL;

import static java.util.Collections.emptySet;
import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkCreatorTest {

    private CreateBookmark createBookmark;
    private Bookmarks bookmarks;
    private URL url;
    private String name;

    @Before
    public void set_up() throws Exception {
        bookmarks = new InMemoryBookmarks();
        createBookmark = new BookmarkCreator(bookmarks);
        url = new URL("http://www.test.com");
        name = "Some name";
    }

    @Test
    public void should_create_the_bookmark() {
        Bookmark createdBookmark = createBookmark.forResource(url, name, emptySet());
        Bookmark expected = new Bookmark(url, name);

        assertThat(createdBookmark).isEqualTo(expected);
    }

    @Test(expected = AlreadyBookmarkedException.class)
    public void should_not_create_the_bookmark_if_it_already_exists() {
        bookmarks.save(new Bookmark(url, name));
        createBookmark.forResource(url, name, emptySet());
    }

}