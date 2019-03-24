package org.craftsrecords.rememberme.rest;

import org.craftsrecords.rememberme.api.CreateBookmark;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/bookmarks")
public class BookmarkController {

    private CreateBookmark createBookmark;

    public BookmarkController(CreateBookmark createBookmark) {
        this.createBookmark = createBookmark;
    }

    @PostMapping
    public ResponseEntity createBookmark(@RequestBody BookmarkPayload bookmarkPayload) throws MalformedURLException {
        createBookmark.forResource(new URL(bookmarkPayload.url), bookmarkPayload.name, bookmarkPayload.tags);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @ExceptionHandler(MalformedURLException.class)
    public void handle(HttpServletResponse response, MalformedURLException exception) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, exception.getMessage());
    }

}
