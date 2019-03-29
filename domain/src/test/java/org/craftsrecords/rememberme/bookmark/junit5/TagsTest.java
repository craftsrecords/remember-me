package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.bookmark.Tags;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

class TagsTest implements EqualityTest<Tags> {

    @Override
    public Tags createValue() {
        return Tags.of(emptySet());
    }

    @Override
    public Tags createOtherValue() {
        return Tags.of(singleton("tag"));
    }

}
