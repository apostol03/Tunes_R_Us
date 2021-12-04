package com.yankovltd.tunes.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity {

    private String name;

    private Artist artist;
    private Album album;

    public Song() {
    }

    public String getName() {
        return name;
    }

    public Song setName(String name) {
        this.name = name;
        return this;
    }

    @ManyToOne
    public Artist getArtist() {
        return artist;
    }

    public Song setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }


    @ManyToOne
    public Album getAlbum() {
        return album;
    }

    public Song setAlbum(Album album) {
        this.album = album;
        return this;
    }
}
