package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.bookmark.BookmarksFinder;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Collection;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class BookmarksFinderTest {

    private static Bookmarks bookmarks;
    private static FindBookmarks findBookmarks;

    @BeforeAll
    static void setUp() {
        bookmarks = new InMemoryBookmarks();
        findBookmarks = new BookmarksFinder(bookmarks);
    }

    @MethodSource
    @DisplayName("Should find the bookmarks by tag")
    @ParameterizedTest(name = "\"{0}\" tag")
    void findByTag(String tag, Bookmark[] expected) {
        Collection<Bookmark> bookmarks = findBookmarks.by(singleton(tag));

        assertThat(bookmarks).containsExactlyInAnyOrder(expected);
    }

    static Stream<Arguments> findByTag() {
        Bookmark junit = saveBookmark("https://junit.org", "JUnit", "tests");
        Bookmark cucumber = saveBookmark("https://cucumber.io", "Cucumber", "tests", "bdd");
        Bookmark bdd = saveBookmark("https://en.wikipedia.org/wiki/BDD", "BDD", "bdd", "methodo");

        return Stream.of(
                arguments("tests", new Bookmark[]{junit, cucumber}),
                arguments("bdd", new Bookmark[]{cucumber, bdd}),
                arguments("methodo", new Bookmark[]{bdd})
        );
    }

    private static Bookmark saveBookmark(String url, String name, String... tags) {
        Bookmark bookmark = Bookmark.create(url, name, asList(tags));
        return bookmarks.save(bookmark);
    }
}