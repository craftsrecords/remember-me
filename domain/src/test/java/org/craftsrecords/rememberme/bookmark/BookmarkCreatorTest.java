package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.BeforeClass;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkCreatorTest {

    private static URL url;

    @BeforeClass
    public static void setUp() throws MalformedURLException {
        url = new URL("http://www.test.com");
    }

    @Test
    public void should_create_the_bookmark() {
        CreateBookmark createBookmark = new BookmarkCreator(new InMemoryBookmarks());

        Bookmark createdBookmark = createBookmark.forResource(url);
        assertThat(createdBookmark.getUrl()).isEqualTo(url);
    }

    @Test(expected = AlreadyBookmarkedException.class)
    public void should_not_create_the_bookmark_if_it_already_exists() {
        Bookmarks bookmarks = (bookmark) -> {
            throw new AlreadyBookmarkedException(bookmark.getUrl());
        };
        CreateBookmark createBookmark = new BookmarkCreator(bookmarks);

        createBookmark.forResource(url);
    }

}