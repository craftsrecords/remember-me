package org.craftsrecords.rememberme.repository;

import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.Optional;

import static java.util.Collections.singleton;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
public class BookmarkRepositoryTest {

    @Autowired
    private JpaBookmarkRepository jpaBookmarkRepository;

    private Bookmarks bookmarks;
    private Bookmark bookmark;

    @Before
    public void set_up() {
        bookmarks = new BookmarkRepository(jpaBookmarkRepository);
        bookmark = Bookmark.create("http://www.test.com", "test", singleton("tag"));
    }

    @Test
    public void should_save_bookmark() {
        Bookmark saved = bookmarks.save(bookmark);
        assertThat(saved).isEqualTo(bookmark);
    }

    @Test
    public void should_retrieve_bookmark_by_url() {
        jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

        Optional<Bookmark> retrieved = bookmarks.getBy(bookmark.getUrl());
        assertThat(retrieved).hasValue(bookmark);
    }

    @Test
    public void should_retrieve_all_bookmarks() {
        jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

        Collection<Bookmark> retrieved = bookmarks.getAll();
        assertThat(retrieved).contains(bookmark);
    }

}