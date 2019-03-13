package org.craftsrecords.rememberme.features.stepdefs;

import cucumber.api.java8.En;
import org.craftsrecords.rememberme.api.CreateBookmark;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class BookmarkStepDefs implements En {

    public BookmarkStepDefs(TestContext context,
                            CreateBookmark createBookmark) {

        Given("^a link towards a useful resource$",
                () -> context.link = new URL("https://junit.org/junit5"));

        When("^I bookmark it$",
                () -> context.createdBookmark = createBookmark.forResource(context.link));

        Then("^it is saved among my other bookmarks$",
                () -> assertThat(context.createdBookmark).isNotNull());

    }

}
