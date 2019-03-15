package org.craftsrecords.rememberme.features.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;

import java.net.URL;

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
                    bookmarks.save(new Bookmark(url));
                    context.link = url;
                });

        When("^I bookmark it$",
                () -> {
                    try {
                        context.createdBookmark = createBookmark.forResource(context.link);
                    } catch (AlreadyBookmarkedException e) {
                        context.alreadyBookmarked = true;
                    }
                });

        Then("^it is saved among my other bookmarks$",
                () -> assertThat(context.createdBookmark).isNotNull());

        Then("^I am notified that the bookmark already exists$",
                () -> assertThat(context.alreadyBookmarked).isTrue());

    }

}
