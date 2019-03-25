package org.craftsrecords.rememberme.bookmark;

import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.stubs.InMemoryBookmarks;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Collection;

import static java.util.Arrays.asList;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.runners.Parameterized.Parameter;

@RunWith(Parameterized.class)
public class BookmarksFinderTest {

    private static Bookmarks bookmarks;
    private static FindBookmarks findBookmarks;

    @Parameters
    public static Collection<Object[]> data() {
        bookmarks = new InMemoryBookmarks();
        findBookmarks = new BookmarksFinder(bookmarks);

        Bookmark junit = saveBookmark("https://junit.org", "JUnit", "tests");
        Bookmark cucumber = saveBookmark("https://cucumber.io", "Cucumber", "tests", "bdd");
        Bookmark bdd = saveBookmark("https://en.wikipedia.org/wiki/BDD", "BDD", "bdd", "methodo");

        return Arrays.asList(new Object[][]{
                {"tests", new Bookmark[]{junit, cucumber}},
                {"bdd", new Bookmark[]{cucumber, bdd}},
                {"methodo", new Bookmark[]{bdd}}
        });
    }

    @Parameter
    public String tag;

    @Parameter(1)
    public Bookmark[] expected;

    @Test
    public void should_retrieve_bookmarks_by_tags() {
        Collection<Bookmark> bookmarks = findBookmarks.by(singleton(tag));

        assertThat(bookmarks).containsExactlyInAnyOrder(expected);
    }

    private static Bookmark saveBookmark(String url, String name, String... tags) {
        try {
            Bookmark bookmark = new Bookmark(new URL(url), name, new Tags(asList(tags)));
            return bookmarks.save(bookmark);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}