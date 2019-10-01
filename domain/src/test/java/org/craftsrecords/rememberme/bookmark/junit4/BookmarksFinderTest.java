package org.craftsrecords.rememberme.bookmark.junit4;

import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.bookmark.BookmarksFinder;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class BookmarksFinderTest {

    @Parameter(0)
    public List<Bookmark> existingBookmarks;

    @Parameter(1)
    public String tag;

    @Parameter(2)
    public List<Bookmark> expectedBookmarks;

    @Test
    public void should_find_bookmarks_by_tag() {
        Bookmarks bookmarks = saveBookmarks(existingBookmarks);
        FindBookmarks findBookmarks = new BookmarksFinder(bookmarks);

        Collection<Bookmark> found = findBookmarks.by(tag);

        assertThat(found).containsAll(expectedBookmarks);
    }

    @Parameters
    public static Collection<Object[]> testCases() {
        Bookmark junit = Bookmark.create("https://junit.org", "JUnit", singletonList("tests"));
        Bookmark cucumber = Bookmark.create("https://cucumber.io", "Cucumber", asList("tests", "bdd"));
        Bookmark bdd = Bookmark.create("https://en.wikipedia.org/wiki/BDD", "BDD", asList("bdd", "methodo"));

        return Arrays.asList(new Object[][]{
                {asList(bdd, junit, cucumber), "tests", asList(junit, cucumber)},
                {asList(bdd, junit, cucumber), "bdd", asList(cucumber, bdd)},
                {asList(bdd, junit, cucumber), "methodo", singletonList(bdd)}
        });
    }

    private Bookmarks saveBookmarks(List<Bookmark> bookmarks) {
        Bookmarks inMemoryBookmarks = new InMemoryBookmarks();
        bookmarks.forEach(inMemoryBookmarks::save);
        return inMemoryBookmarks;
    }

}