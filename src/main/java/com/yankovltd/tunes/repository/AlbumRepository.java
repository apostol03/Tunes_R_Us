package com.yankovltd.tunes.repository;

import com.yankovltd.tunes.model.entity.Album;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Long> {

    @Query("SELECT a FROM Album a WHERE " +
            "a.name LIKE %?1% OR a.artist.name LIKE %?1% ORDER BY a.yearReleased DESC ")
    List<Album> search(String query);

    @EntityGraph(value = "albums_entity_graph")
    List<Album> findAllBy();
}
