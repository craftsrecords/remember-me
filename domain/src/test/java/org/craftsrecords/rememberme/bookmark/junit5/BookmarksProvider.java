package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class BookmarksProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        Bookmark junit = Bookmark.create("https://junit.org", "JUnit", singletonList("tests"));
        Bookmark cucumber = Bookmark.create("https://cucumber.io", "Cucumber", asList("tests", "bdd"));
        Bookmark bdd = Bookmark.create("https://en.wikipedia.org/wiki/BDD", "BDD", asList("bdd", "methodo"));

        return Stream.of(
                arguments(asList(bdd, junit, cucumber), "tests", asList(junit, cucumber)),
                arguments(asList(bdd, junit, cucumber), "bdd", asList(cucumber, bdd)),
                arguments(asList(bdd, junit, cucumber), "methodo", singletonList(bdd))
        );
    }

}
