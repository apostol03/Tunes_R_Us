package com.yankovltd.tunes.model.view;

public class SongFavoriteViewModel {

    private Long id;
    private String name;
    private String artist;
    private AlbumViewModel album;

    public SongFavoriteViewModel() {
    }

    public String getName() {
        return name;
    }

    public SongFavoriteViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getArtist() {
        return artist;
    }

    public SongFavoriteViewModel setArtist(String artist) {
        this.artist = artist;
        return this;
    }

    public AlbumViewModel getAlbum() {
        return album;
    }

    public SongFavoriteViewModel setAlbum(AlbumViewModel album) {
        this.album = album;
        return this;
    }

    public Long getId() {
        return id;
    }

    public SongFavoriteViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
