package org.craftsrecords.rememberme.junit5.repository;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

public class BookmarkParameterResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.isAnnotated(Random.class) &&
                parameterContext.getParameter().getType() == Bookmark.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        List<Bookmark> bookmarks = createBookmarks();
        Collections.shuffle(bookmarks);
        return bookmarks.get(0);
    }

    private List<Bookmark> createBookmarks() {
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(Bookmark.create("http://www.junit.org", "JUnit", singleton("test")));
        bookmarks.add(Bookmark.create("http://www.medium.com", "Medium", emptySet()));
        bookmarks.add(Bookmark.create("http://www.youtube.com", "YouTube", singleton("videos")));
        return bookmarks;
    }

}
