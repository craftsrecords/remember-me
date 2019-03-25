package org.craftsrecords.rememberme.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Collections.singletonList;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void should_respond_201_when_the_bookmark_is_created() throws Exception {
        BookmarkPayload bookmarkPayload = new BookmarkPayload(
                "http://www.test.com",
                "name",
                singletonList("tag")
        );

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isCreated());
    }

    @Test
    public void should_respond_400_when_the_request_is_invalid() throws Exception {
        BookmarkPayload bookmarkPayload = new BookmarkPayload(
                "invalid://url.com",
                "name",
                singletonList("tag")
        );

        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void should_respond_200_when_a_search_is_successfully_done() throws Exception {
        mockMvc.perform(
                get("/bookmarks")
                        .param("tags", "craftsmanship", "clean-code"))
                .andExpect(status().isOk());
    }

}