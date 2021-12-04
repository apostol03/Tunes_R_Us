package com.yankovltd.tunes.repository;

import com.yankovltd.tunes.model.entity.Artist;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    @Query("SELECT a FROM Artist a WHERE a.name LIKE %?1%")
    List<Artist> search(String query);

    @EntityGraph(value = "artists_entity_graph")
    Optional<Artist> findById(Long id);
}
