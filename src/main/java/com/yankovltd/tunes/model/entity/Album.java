package com.yankovltd.tunes.model.entity;

import com.yankovltd.tunes.model.entity.enums.GenreEnum;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "albums")
@NamedEntityGraph(name = "albums_entity_graph",
        attributeNodes = {
                @NamedAttributeNode("picture"),
                @NamedAttributeNode("artist"),
                @NamedAttributeNode("songs")
        }
)
public class Album extends BaseEntity {

    private String name;
    private Integer yearReleased;

    private GenreEnum genre;
    private Artist artist;
    private Picture picture;
    private List<Song> songs;

    public Album() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Album setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    public GenreEnum getGenre() {
        return genre;
    }

    public Album setGenre(GenreEnum genre) {
        this.genre = genre;
        return this;
    }

    @Column(nullable = false)
    public Integer getYearReleased() {
        return yearReleased;
    }

    public Album setYearReleased(Integer yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    @ManyToOne
    public Artist getArtist() {
        return artist;
    }

    public Album setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    @OneToOne(fetch = FetchType.LAZY)
    public Picture getPicture() {
        return picture;
    }

    public Album setPicture(Picture picture) {
        this.picture = picture;
        return this;
    }

    @OneToMany(mappedBy = "album", fetch = FetchType.LAZY)
    public List<Song> getSongs() {
        return songs;
    }

    public Album setSongs(List<Song> songs) {
        this.songs = songs;
        return this;
    }
}
