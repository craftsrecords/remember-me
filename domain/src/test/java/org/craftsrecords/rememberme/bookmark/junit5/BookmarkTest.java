package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookmarkTest implements EqualityTest<Bookmark> {

    @ParameterizedTest(name = "{1}")
    @CsvSource({
            "http://www.test.com, Some name, tag",
            "http://www.test.com/path, Some other name, another-tag"
    })
    @DisplayName("Should create the bookmark")
    void should_create_the_bookmark(String url, String name, String tag) {
        Set<String> tags = singleton(tag);
        Bookmark createdBookmark = Bookmark.create(url, name, tags);

        assertAll(
                () -> assertThat(createdBookmark.getUrl()).isEqualTo(url),
                () -> assertThat(createdBookmark.getName()).isEqualTo(name),
                () -> assertThat(createdBookmark.getTags()).isEqualTo(tags)
        );
    }

    @Test
    @DisplayName("Should not accept an invalid url")
    void should_not_accept_an_invalid_url() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Bookmark.create("invalid url", "name", emptySet())
        );
    }

    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t"})
    @DisplayName("Should not accept null, empty or blank name")
    @ParameterizedTest(name = "\"{0}\"")
    void should_not_accept_a_invalid_name(String name) {
        Exception exception = assertThrows(
                IllegalArgumentException.class,
                () -> Bookmark.create("http://www.test.com", name, emptySet())
        );
        assertThat(exception.getMessage()).isEqualTo("Invalid name: it should not be empty");
    }

    @Override
    public Bookmark createValue() {
        return Bookmark.create("http://www.test.com", "name", emptySet());
    }

    @Override
    public Bookmark createOtherValue() {
        return Bookmark.create("http://www.test2.com", "other name", singleton("tag"));
    }

}