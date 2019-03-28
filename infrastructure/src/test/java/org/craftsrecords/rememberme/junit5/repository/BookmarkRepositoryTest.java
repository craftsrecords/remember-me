package org.craftsrecords.rememberme.junit5.repository;

import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.repository.BookmarkEntity;
import org.craftsrecords.rememberme.repository.BookmarkRepository;
import org.craftsrecords.rememberme.repository.JpaBookmarkRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class BookmarkRepositoryTest {

    private static final String URL = "http://www.test.com";

    @Autowired
    private JpaBookmarkRepository jpaBookmarkRepository;

    private BookmarkRepository bookmarkRepository;
    private Bookmark bookmark;

    @BeforeEach
    void set_up() {
        bookmarkRepository = new BookmarkRepository(jpaBookmarkRepository);
        bookmark = Bookmark.create(URL, "test", singleton("tag"));
    }

    @Test
    @DisplayName("Should save a bookmark")
    void should_save_bookmark() {
        Bookmark saved = bookmarkRepository.save(bookmark);
        assertThat(saved).isEqualTo(bookmark);
    }

    @Nested
    @DisplayName("When a bookmark is saved")
    class WhenBookmarkIsSaved {

        @BeforeEach
        void set_up() {
            jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));
        }

        @Test
        @DisplayName("it's not possible to save it again")
        void should_not_save_an_already_existent_bookmark() {
            assertThrows(
                    AlreadyBookmarkedException.class,
                    () -> bookmarkRepository.save(bookmark),
                    URL + " is already bookmarked"
            );
        }

        @Test
        @DisplayName("it should be accessible")
        void should_retrieve_bookmark_by_url() {
            Optional<Bookmark> retrieved = bookmarkRepository.getBy(URL);
            assertThat(retrieved).hasValue(bookmark);
        }

        @Test
        @DisplayName("it should be accessible among all bookmarks")
        void should_retrieve_all_bookmarks() {
            Collection<Bookmark> retrieved = bookmarkRepository.getAll();
            assertThat(retrieved).containsOnly(bookmark);
        }

    }

}