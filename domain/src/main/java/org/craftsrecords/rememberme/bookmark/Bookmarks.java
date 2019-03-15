package org.craftsrecords.rememberme.bookmark;

public interface Bookmarks {

    Bookmark save(Bookmark bookmark) throws AlreadyBookmarkedException;

}
