package com.yankovltd.tunes.repository;

import com.yankovltd.tunes.model.entity.LikedAlbum;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedAlbumRepository extends JpaRepository<LikedAlbum, Long> {

    @EntityGraph(value = "liked_albums_entity_graph")
    List<LikedAlbum> findAllByUserEntityId(Long userId);

    List<LikedAlbum> findByUserEntityId(Long userId);

    Optional<LikedAlbum> findByAlbumId(Long albumId);
}
