package org.craftsrecords.rememberme.repository;

import org.craftsrecords.rememberme.bookmark.AlreadyBookmarkedException;
import org.craftsrecords.rememberme.bookmark.Bookmark;
import org.craftsrecords.rememberme.bookmark.Bookmarks;

import java.net.URL;
import java.util.Optional;

public class BookmarkRepository implements Bookmarks {

    private JpaBookmarkRepository repository;

    public BookmarkRepository(JpaBookmarkRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bookmark save(Bookmark bookmark) throws AlreadyBookmarkedException {
        URL url = bookmark.getUrl();
        repository.findByUrl(url).ifPresent(b -> {
            throw new AlreadyBookmarkedException(url);
        });

        BookmarkEntity entity = BookmarkEntity.from(bookmark);
        return repository.save(entity).toValueObject();
    }

    @Override
    public Optional<Bookmark> getBy(URL url) {
        return repository.findByUrl(url)
                .map(BookmarkEntity::toValueObject);
    }

}
