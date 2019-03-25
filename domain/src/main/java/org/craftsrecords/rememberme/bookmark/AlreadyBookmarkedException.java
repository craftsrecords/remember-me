package org.craftsrecords.rememberme.bookmark;

public class AlreadyBookmarkedException extends RuntimeException {

    private static final String MESSAGE = "%s is already bookmarked";

    public AlreadyBookmarkedException(String url) {
        super(String.format(MESSAGE, url));
    }

}
