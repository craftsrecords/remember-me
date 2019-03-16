package org.craftsrecords.rememberme.features.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.craftsrecords.rememberme.bookmark.Tags;

import java.net.URL;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkStepDefs implements En {

    public BookmarkStepDefs(TestContext context,
                            CreateBookmark createBookmark,
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

        When("^I bookmark it$",
                () -> {
                    try {
                        createBookmark.forResource(context.link, context.name, context.tags);
                    } catch (AlreadyBookmarkedException e) {
                        context.alreadyBookmarked = true;
                    }
                });

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

    }

}
