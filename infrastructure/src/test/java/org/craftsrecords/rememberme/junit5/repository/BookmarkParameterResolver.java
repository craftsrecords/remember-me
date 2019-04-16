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
        return parameterContext.getParameter().getType() == Bookmark.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        if (parameterContext.isAnnotated(Random.class)) {
            return randomBookmark();
        }
        if (parameterContext.isAnnotated(Video.class)) {
            return Bookmark.create("http://www.youtube.com/watch?v=ABCD", "Cool video", emptySet());
        }
        return Bookmark.create("http://www.google.com", "Default bookmark", emptySet());
    }

    private Bookmark randomBookmark() {
        List<Bookmark> bookmarks = new ArrayList<>();
        bookmarks.add(Bookmark.create("http://www.junit.org", "JUnit", singleton("test")));
        bookmarks.add(Bookmark.create("http://www.medium.com", "Medium", emptySet()));
        bookmarks.add(Bookmark.create("http://www.youtube.com", "YouTube", singleton("videos")));
        Collections.shuffle(bookmarks);
        return bookmarks.get(0);
    }

}
