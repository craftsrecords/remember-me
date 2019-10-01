package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.bookmark.BookmarksFinder;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsSource;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class BookmarksFinderTest {

    @DisplayName("Should find the bookmarks by tag")
    @ParameterizedTest(name = "\"{1}\" tag")
    @ArgumentsSource(BookmarksProvider.class)
    void findByTag(List<Bookmark> existingBookmarks, String tag,
                   List<Bookmark> expectedBookmarks) {
        Bookmarks bookmarks = saveBookmarks(existingBookmarks);
        FindBookmarks findBookmarks = new BookmarksFinder(bookmarks);

        Collection<Bookmark> found = findBookmarks.by(tag);

        assertThat(found).containsAll(expectedBookmarks);
    }

    private Bookmarks saveBookmarks(List<Bookmark> bookmarks) {
        Bookmarks inMemoryBookmarks = new InMemoryBookmarks();
        bookmarks.forEach(inMemoryBookmarks::save);
        return inMemoryBookmarks;
    }

}