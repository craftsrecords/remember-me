package org.craftsrecords.rememberme.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.repository.BookmarkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookmarkRepository bookmarkRepository;

    @Test
    public void should_respond_201_when_the_bookmark_is_created() throws Exception {
        BookmarkPayload bookmarkPayload = new BookmarkPayload(
                "http://www.test.com",
                "A test link",
                singletonList("good-stuff")
        );

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_respond_409_when_the_bookmark_already_exists() throws Exception {
        Bookmark bookmark = Bookmark.create(
                "http://www.test2.com",
                "A test link",
                singletonList("good-stuff")
        );
        bookmarkRepository.save(bookmark);

        BookmarkPayload bookmarkPayload = new BookmarkPayload(
                "http://www.test2.com",
                "A test link",
                singletonList("good-stuff")
        );

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isConflict());
    }

    @Test
    public void should_respond_200_when_a_search_is_successfully_done() throws Exception {
        Bookmark bookmark = Bookmark.create(
                "http://www.test3.com",
                "A test link",
                singletonList("good-stuff")
        );
        bookmarkRepository.save(bookmark);

        mockMvc.perform(
                get("/bookmarks")
                        .param("tag", "good-stuff"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", is(bookmark.getName())));
    }
}