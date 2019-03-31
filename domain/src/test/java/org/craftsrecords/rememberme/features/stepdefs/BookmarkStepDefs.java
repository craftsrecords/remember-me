package org.craftsrecords.rememberme.features.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkStepDefs implements En {

    public BookmarkStepDefs(TestContext context,
                            CreateBookmark createBookmark,
                            FindBookmarks findBookmarks,
                            Bookmarks bookmarks) {

        Given("^a link towards a useful resource$",
                () -> context.link = "https://junit.org/junit5");

        Given("^a link that I have bookmarked$",
                () -> {
                    Bookmark bookmark = Bookmark.create("https://junit.org/junit5", "JUnit", emptySet());
                    bookmarks.save(bookmark);
                    context.link = bookmark.getUrl();
                    context.name = bookmark.getName();
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

        Given("^a theme I want to read about$",
                () -> context.searchedTag = "test");

        When("^I bookmark it$",
                () -> {
                    try {
                        createBookmark.forResource(context.link, context.name, context.tags);
                    } catch (AlreadyBookmarkedException e) {
                        context.alreadyBookmarked = true;
                    }
                });

        When("^I search for bookmarks about this theme$",
                () -> context.searchResults = findBookmarks.by(context.searchedTag));

        Then("^it is saved among my other bookmarks$",
                () -> {
                    Optional<Bookmark> bookmark = bookmarks.getBy(context.link);
                    assertThat(bookmark).isPresent();

                    Bookmark expected = Bookmark.create(
                            context.link,
                            context.name,
                            context.tags
                    );
                    assertThat(bookmark).hasValue(expected);
                });

        Then("^I am notified that the bookmark already exists$",
                () -> assertThat(context.alreadyBookmarked).isTrue());

        Then("^I get bookmarks tagged with it$",
                () -> context.searchResults.forEach(
                        bookmark -> assertThat(bookmark.hasTag(context.searchedTag)).isTrue()
                ));

    }

    private List<Bookmark> createBookmarks() {
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(Bookmark.create("https://gitlab.com", "GitLab", singleton("code")));
        bookmarks.add(Bookmark.create("https://junit.org/junit5", "JUnit", singleton("test")));
        bookmarks.add(Bookmark.create("https://news.ycombinator.com/news", "HN", singleton("news")));
        return bookmarks;
    }

}
