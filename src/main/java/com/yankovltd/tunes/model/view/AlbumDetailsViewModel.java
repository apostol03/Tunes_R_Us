package com.yankovltd.tunes.model.view;

import com.yankovltd.tunes.model.entity.Artist;
import com.yankovltd.tunes.model.entity.Song;

import java.util.List;
import java.util.Set;

public class AlbumDetailsViewModel {

    private Long id;
    private String name;
    private String genre;
    private Artist artist;
    private String picture;
    private Integer yearReleased;
    private Integer songsCount;
    private List<Song> songs;

    public AlbumDetailsViewModel() {
    }

    public String getName() {
        return name;
    }

    public AlbumDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Artist getArtist() {
        return artist;
    }

    public AlbumDetailsViewModel setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public AlbumDetailsViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public Integer getSongsCount() {
        return songsCount;
    }

    public AlbumDetailsViewModel setSongsCount(Integer songsCount) {
        this.songsCount = songsCount;
        return this;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public AlbumDetailsViewModel setSongs(List<Song> songs) {
        this.songs = songs;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AlbumDetailsViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getYearReleased() {
        return yearReleased;
    }

    public AlbumDetailsViewModel setYearReleased(Integer yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public AlbumDetailsViewModel setGenre(String genre) {
        this.genre = genre;
        return this;
    }
}
