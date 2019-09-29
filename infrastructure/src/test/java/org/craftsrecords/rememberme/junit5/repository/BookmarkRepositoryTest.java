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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@ExtendWith(SpringExtension.class)
@InjectBookmark
/*
InjectBookmark can be set globally and removed from this test by adding in META-INF/services/ org.junit.jupiter.api.extension.Extension
and set junit.jupiter.extensions.autodetection.enabled to true in the surefire configuration
 */
class BookmarkRepositoryTest {

    @Autowired
    private JpaBookmarkRepository jpaBookmarkRepository;

    private BookmarkRepository bookmarkRepository;

    @BeforeEach
    void set_up() {
        bookmarkRepository = new BookmarkRepository(jpaBookmarkRepository);
    }

    @Test
    @DisplayName("Should save a bookmark")
    void should_save_bookmark(Bookmark bookmark) {
        Bookmark saved = bookmarkRepository.save(bookmark);
        assertThat(saved).isEqualTo(bookmark);
    }

    @Nested
    @DisplayName("When a bookmark is saved")
    class WhenBookmarkIsSaved {

        private Bookmark savedBookmark;

        @BeforeEach
        void set_up(@Random Bookmark bookmark) {
            jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));
            savedBookmark = bookmark;
        }

        @Test
        @DisplayName("it's not possible to save it again")
        void should_not_save_an_already_existent_bookmark() {
            assertThrows(
                    AlreadyBookmarkedException.class,
                    () -> bookmarkRepository.save(savedBookmark),
                    savedBookmark.getUrl() + " is already bookmarked"
            );
        }

        @Test
        @DisplayName("it should be accessible")
        void should_retrieve_bookmark_by_url() {
            String url = savedBookmark.getUrl();
            Optional<Bookmark> retrieved = bookmarkRepository.getBy(url);
            assertThat(retrieved).hasValue(savedBookmark);
        }

        @Test
        @DisplayName("it should be accessible among all bookmarks")
        void should_retrieve_all_bookmarks() {
            Collection<Bookmark> retrieved = bookmarkRepository.getAll();
            assertThat(retrieved).contains(savedBookmark);
        }
    }
}