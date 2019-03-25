package org.craftsrecords.rememberme.rest;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.craftsrecords.rememberme.api.FindBookmarks;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
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
    public ResponseEntity createBookmark(@RequestBody BookmarkPayload bookmarkPayload) throws MalformedURLException {
        createBookmark.forResource(new URL(bookmarkPayload.url), bookmarkPayload.name, bookmarkPayload.tags);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<Collection<Bookmark>> getBookmarksByTags(@RequestParam("tags") Collection<String> tags) {
        Collection<Bookmark> bookmarks = findBookmarks.by(tags);
        return ResponseEntity.ok(bookmarks);
    }

    @ExceptionHandler(MalformedURLException.class)
    public void handle(HttpServletResponse response, MalformedURLException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

}
