package org.craftsrecords.rememberme.bookmark;

import java.net.URL;

public class AlreadyBookmarkedException extends RuntimeException {

    private static final String MESSAGE = "%s is already bookmarked";

    public AlreadyBookmarkedException(URL url) {
        super(String.format(MESSAGE, url));
    }

}
