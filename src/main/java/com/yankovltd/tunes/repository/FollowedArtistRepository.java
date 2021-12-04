package com.yankovltd.tunes.repository;

import com.yankovltd.tunes.model.entity.FollowedArtist;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowedArtistRepository extends JpaRepository<FollowedArtist, Long> {

    @EntityGraph(value = "followed_artists_entity_graph")
    List<FollowedArtist> findAllByUserEntityId(Long id);

    List<FollowedArtist> findByUserEntityId(Long userId);

    Optional<FollowedArtist> findByArtistId(Long artistId);
}
