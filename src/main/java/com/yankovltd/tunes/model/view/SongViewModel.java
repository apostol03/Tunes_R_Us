package com.yankovltd.tunes.model.view;

public class SongViewModel {

    private Long id;
    private String name;
    private Long albumId;

    public SongViewModel() {
    }

    public Long getId() {
        return id;
    }

    public SongViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public SongViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Long getAlbumId() {
        return albumId;
    }

    public SongViewModel setAlbumId(Long albumId) {
        this.albumId = albumId;
        return this;
    }
}
