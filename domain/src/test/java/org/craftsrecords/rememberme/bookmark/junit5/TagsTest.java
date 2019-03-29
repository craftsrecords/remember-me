package org.craftsrecords.rememberme.bookmark.junit5;

import org.craftsrecords.rememberme.bookmark.Tags;

import static java.util.Collections.emptySet;
import static java.util.Collections.singleton;

class TagsTest implements EqualityTest<Tags> {

    @Override
    public Tags createValue() {
        return new Tags(emptySet());
    }

    @Override
    public Tags createOtherValue() {
        return new Tags(singleton("tag"));
    }

}
