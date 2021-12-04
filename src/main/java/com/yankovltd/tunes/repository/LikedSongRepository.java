package com.yankovltd.tunes.repository;

import com.yankovltd.tunes.model.entity.LikedSong;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikedSongRepository extends JpaRepository<LikedSong, Long> {

    @EntityGraph(value = "liked_songs_entity_graph")
    List<LikedSong> findAllByUserEntityId(Long userId);

    List<LikedSong> findByUserEntityId(Long userId);

    Optional<LikedSong> findBySongId(Long songId);
}
