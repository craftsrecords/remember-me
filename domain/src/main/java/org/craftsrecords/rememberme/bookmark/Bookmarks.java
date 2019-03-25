package org.craftsrecords.rememberme.bookmark;

import java.net.URL;
import java.util.Collection;
import java.util.Optional;

public interface Bookmarks {

    Bookmark save(Bookmark bookmark) throws AlreadyBookmarkedException;
    Optional<Bookmark> getBy(URL url);

    Collection<Bookmark> getAll();

}
