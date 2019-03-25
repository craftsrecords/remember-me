package org.craftsrecords.rememberme.repository;

import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
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

    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private JpaBookmarkRepository jpaBookmarkRepository;

    private BookmarkRepository bookmarkRepository;
    private Bookmark bookmark;
    private String url;

    @Before
    public void set_up() {
        bookmarkRepository = new BookmarkRepository(jpaBookmarkRepository);
        url = "http://www.test.com";
        bookmark = Bookmark.create(url, "test", singleton("tag"));
    }

    @Test
    public void should_save_bookmark() {
        Bookmark saved = bookmarkRepository.save(bookmark);
        assertThat(saved).isEqualTo(bookmark);
    }

    @Test
    public void should_not_save_an_already_existent_bookmark() {
        bookmarkRepository.save(bookmark);

        exception.expect(AlreadyBookmarkedException.class);
        bookmarkRepository.save(bookmark);
    }

    @Test
    public void should_retrieve_bookmark_by_url() {
        jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

        Optional<Bookmark> retrieved = bookmarkRepository.getBy(url);
        assertThat(retrieved).hasValue(bookmark);
    }

    @Test
    public void should_retrieve_all_bookmarks() {
        jpaBookmarkRepository.save(BookmarkEntity.from(bookmark));

        Collection<Bookmark> retrieved = bookmarkRepository.getAll();
        assertThat(retrieved).containsOnly(bookmark);
    }

}