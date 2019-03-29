package org.craftsrecords.rememberme.bookmark;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class Tags {

    private Set<String> tags;

    private Tags(Collection<String> tags) {
        this.tags = new HashSet<>(tags);
    }

    public static Tags of(Collection<String> tags) {
        return new Tags(new HashSet<>(tags));
    }

    Set<String> toSet() {
        return Collections.unmodifiableSet(tags);
    }

    boolean contains(String tag) {
        return tags.contains(tag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return tags.equals(((Tags) o).tags);
    }

    @Override
    public int hashCode() {
        return tags.hashCode();
    }
}
