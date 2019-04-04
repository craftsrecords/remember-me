package org.craftsrecords.rememberme.bookmark;

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
        return randomBookmark();
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
