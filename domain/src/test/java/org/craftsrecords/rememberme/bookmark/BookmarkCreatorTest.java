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

    @Before
    public void set_up() throws Exception {
        bookmarks = new InMemoryBookmarks();
        createBookmark = new BookmarkCreator(bookmarks);
        url = new URL("http://www.test.com");
    }

    @Test
    public void should_create_the_bookmark() {
        Bookmark createdBookmark = createBookmark.forResource(url, emptySet());
        Bookmark expected = new Bookmark(url, Tags.empty());

        assertThat(createdBookmark).isEqualTo(expected);
    }

    @Test(expected = AlreadyBookmarkedException.class)
    public void should_not_create_the_bookmark_if_it_already_exists() {
        bookmarks.save(new Bookmark(url));
        createBookmark.forResource(url, emptySet());
    }

}