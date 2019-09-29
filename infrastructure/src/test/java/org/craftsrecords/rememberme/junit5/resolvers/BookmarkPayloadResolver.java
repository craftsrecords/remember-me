package org.craftsrecords.rememberme.junit5.resolvers;

import org.craftsrecords.rememberme.rest.BookmarkPayload;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.api.extension.ParameterResolutionException;
import org.junit.jupiter.api.extension.ParameterResolver;

import static java.util.Collections.singletonList;

public class BookmarkPayloadResolver implements ParameterResolver {

    @Override
    public boolean supportsParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return parameterContext.getParameter().getType() == BookmarkPayload.class;
    }

    @Override
    public Object resolveParameter(ParameterContext parameterContext, ExtensionContext extensionContext) throws ParameterResolutionException {
        return new BookmarkPayload("http://www.test.com", "A test link", singletonList("good-stuff"));
    }

}
