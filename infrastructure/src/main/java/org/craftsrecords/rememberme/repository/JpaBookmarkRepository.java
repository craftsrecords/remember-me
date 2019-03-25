package org.craftsrecords.rememberme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JpaBookmarkRepository extends CrudRepository<BookmarkEntity, String> {
    Optional<BookmarkEntity> findByUrl(String url);
}
