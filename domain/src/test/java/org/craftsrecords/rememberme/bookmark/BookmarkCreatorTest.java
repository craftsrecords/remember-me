package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkCreatorTest {

    private final CreateBookmark createBookmark = new BookmarkCreator();

    @Test
    public void should_create_the_bookmark() throws MalformedURLException {
        URL url = new URL("http://www.test.com");
        Bookmark createdBookmark = createBookmark.forResource(url);
        assertThat(createdBookmark.getUrl()).isEqualTo(url);
    }

}