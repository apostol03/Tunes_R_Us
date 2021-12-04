package com.yankovltd.tunes.repository;

import com.yankovltd.tunes.model.entity.Song;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {

    Set<Song> findAllByAlbumNameOrderById(String name);
}
