package org.craftsrecords.rememberme.bookmark;

import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import static java.util.Collections.singleton;

public class BookmarkAggregator implements ArgumentsAggregator {
    @Override
    public Bookmark aggregateArguments(ArgumentsAccessor argumentsAccessor, ParameterContext parameterContext) {
        return Bookmark.create(
                argumentsAccessor.getString(0),
                argumentsAccessor.getString(1),
                singleton(argumentsAccessor.getString(2)));
    }
}
