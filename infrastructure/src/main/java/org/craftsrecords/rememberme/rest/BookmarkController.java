package org.craftsrecords.rememberme.rest;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    private CreateBookmark createBookmark;
    private FindBookmarks findBookmarks;

    public BookmarkController(CreateBookmark createBookmark, FindBookmarks findBookmarks) {
        this.createBookmark = createBookmark;
        this.findBookmarks = findBookmarks;
    }

    @PostMapping
    public ResponseEntity createBookmark(@RequestBody BookmarkPayload bookmarkPayload) {
        createBookmark.forResource(bookmarkPayload.url, bookmarkPayload.name, bookmarkPayload.tags);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Collection<Bookmark>> getBookmarksByTags(@RequestParam("tag") String tag) {
        Collection<Bookmark> bookmarks = findBookmarks.by(tag);
        return ResponseEntity.ok(bookmarks);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public void handle(HttpServletResponse response, IllegalArgumentException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

    @ExceptionHandler(AlreadyBookmarkedException.class)
    public void handle(HttpServletResponse response, AlreadyBookmarkedException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_CONFLICT, exception.getMessage());
    }

}
