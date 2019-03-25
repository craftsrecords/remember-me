package org.craftsrecords.rememberme.features.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.bookmark.Tags;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkStepDefs implements En {

    public BookmarkStepDefs(TestContext context,
                            CreateBookmark createBookmark,
                            FindBookmarks findBookmarks,
                            Bookmarks bookmarks) {

        Given("^a link towards a useful resource$",
                () -> context.link = new URL("https://junit.org/junit5"));

        Given("^a link that I have bookmarked$",
                () -> {
                    URL url = new URL("https://junit.org/junit5");
                    bookmarks.save(new Bookmark(url, ""));
                    context.link = url;
                });

        Given("^a name describing the resource$",
                () -> context.name = "A great testing framework");

        Given("^some tags classifying the resource$",
                () -> {
                    context.tags.add("testing");
                    context.tags.add("dev");
                });

        Given("^some bookmarks I saved$",
                () -> createBookmarks().forEach(bookmarks::save));

        Given("^some themes I want to read about$",
                () -> context.searchedTag = "test");

        When("^I bookmark it$",
                () -> {
                    try {
                        createBookmark.forResource(context.link, context.name, context.tags);
                    } catch (AlreadyBookmarkedException e) {
                        context.alreadyBookmarked = true;
                    }
                });

        When("^I search for bookmarks about these themes$",
                () -> context.searchResults = findBookmarks.by(singleton(context.searchedTag)));

        Then("^it is saved among my other bookmarks$",
                () -> {
                    Optional<Bookmark> bookmark = bookmarks.getBy(context.link);
                    assertThat(bookmark).isPresent();

                    Bookmark expected = new Bookmark(
                            context.link,
                            context.name,
                            new Tags(context.tags)
                    );
                    assertThat(bookmark.get()).isEqualTo(expected);
                });

        Then("^I am notified that the bookmark already exists$",
                () -> assertThat(context.alreadyBookmarked).isTrue());

        Then("^I get bookmarks tagged with these themes$",
                () -> context.searchResults.forEach(
                        bookmark -> assertThat(bookmark.hasTag(context.searchedTag)).isTrue()
                ));

    }

    private List<Bookmark> createBookmarks() throws MalformedURLException {
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(createBookmark("https://gitlab.com", "GitLab", "code"));
        bookmarks.add(createBookmark("https://junit.org/junit5", "JUnit", "test"));
        bookmarks.add(createBookmark("https://news.ycombinator.com/news", "HN", "news"));
        return bookmarks;
    }

    private Bookmark createBookmark(String url, String name, String tag) throws MalformedURLException {
        return new Bookmark(new URL(url), name, new Tags(singleton(tag)));
    }

}
