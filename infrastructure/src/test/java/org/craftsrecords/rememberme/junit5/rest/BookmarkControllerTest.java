package org.craftsrecords.rememberme.junit5.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.craftsrecords.rememberme.junit5.resolvers.BookmarkPayloadResolver;
import org.craftsrecords.rememberme.junit5.resolvers.Invalid;
import org.craftsrecords.rememberme.rest.BookmarkPayload;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(OrderAnnotation.class)
@ExtendWith(BookmarkPayloadResolver.class)
class BookmarkControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @Order(1)
    void should_respond_201_when_the_bookmark_is_created(@Autowired ObjectMapper objectMapper,
                                                         BookmarkPayload bookmarkPayload) throws Exception {
        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isCreated());
    }

    @Test
    void should_respond_400_when_the_request_is_invalid(@Autowired ObjectMapper objectMapper,
                                                        @Invalid BookmarkPayload bookmarkPayload) throws Exception {
        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_respond_409_when_the_bookmark_already_exists(@Autowired ObjectMapper objectMapper,
                                                             BookmarkPayload bookmarkPayload) throws Exception {
        mockMvc.perform(
                post("/bookmarks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookmarkPayload)))
                .andExpect(status().isConflict());
    }

    @Test
    void should_respond_200_when_a_search_is_successfully_done() throws Exception {
        mockMvc.perform(
                get("/bookmarks")
                        .param("tag", "good-stuff"))
                .andExpect(status().isOk());
    }
}