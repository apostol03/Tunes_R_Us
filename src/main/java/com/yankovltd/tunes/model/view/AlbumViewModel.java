package com.yankovltd.tunes.model.view;

public class AlbumViewModel {

    private Long id;
    private String name;
    private Integer yearReleased;
    private String genre;
    private String artist;
    private String picture;

    public AlbumViewModel() {
    }

    public String getName() {
        return name;
    }

    public AlbumViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Integer getYearReleased() {
        return yearReleased;
    }

    public AlbumViewModel setYearReleased(Integer yearReleased) {
        this.yearReleased = yearReleased;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public AlbumViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public AlbumViewModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AlbumViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGenre() {
        return genre;
    }

    public AlbumViewModel setGenre(String genre) {
        this.genre = genre;
        return this;
    }
}
