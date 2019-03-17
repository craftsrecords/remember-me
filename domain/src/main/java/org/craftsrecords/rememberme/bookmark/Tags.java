package org.craftsrecords.rememberme.bookmark;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static java.util.Collections.emptySet;

public class Tags {

    private Set<String> tags;

    public Tags(Collection<String> tags) {
        this.tags = new HashSet<>(tags);
    }

    static Tags empty() {
        return new Tags(emptySet());
    }

    public Set<String> toSet() {
        return Collections.unmodifiableSet(tags);
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
