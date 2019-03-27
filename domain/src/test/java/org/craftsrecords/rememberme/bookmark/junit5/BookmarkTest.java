package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BookmarkTest {

    @Test
    void should_create_the_bookmark() {
        String url = "http://www.test.com";
        String name = "Some name";
        Set<String> tags = singleton("tag");
        Bookmark createdBookmark = Bookmark.create(url, name, tags);

        assertAll(
                () -> assertThat(createdBookmark.getUrl()).isEqualTo(url),
                () -> assertThat(createdBookmark.getName()).isEqualTo(name),
                () -> assertThat(createdBookmark.getTags()).isEqualTo(tags)
        );
    }

    @Test
    void should_not_accept_an_invalid_url() {
        assertThrows(
                IllegalArgumentException.class,
                () -> Bookmark.create("invalid url", "name", emptySet())
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {" ", "  ", "\t"})
    @DisplayName("Should not accept null, empty or blank name")
    void should_not_accept_a_invalid_name(String name) {
        assertThrows(
                IllegalArgumentException.class,
                () -> Bookmark.create("http://www.test.com", name, emptySet()),
                "Invalid name: it should not be empty"
        );
    }

}