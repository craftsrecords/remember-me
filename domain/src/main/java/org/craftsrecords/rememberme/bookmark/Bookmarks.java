package org.craftsrecords.rememberme.bookmark;

import java.util.Collection;
import java.util.Optional;

public interface Bookmarks {

    Bookmark save(Bookmark bookmark) throws AlreadyBookmarkedException;

    Optional<Bookmark> getBy(String url);
    Collection<Bookmark> getAll();

}
