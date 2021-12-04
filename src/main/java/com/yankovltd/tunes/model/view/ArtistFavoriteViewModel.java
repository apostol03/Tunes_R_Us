package com.yankovltd.tunes.model.view;

public class ArtistFavoriteViewModel {

    private Long id;
    private String name;
    private String picture;

    public ArtistFavoriteViewModel() {
    }

    public Long getId() {
        return id;
    }

    public ArtistFavoriteViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ArtistFavoriteViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getPicture() {
        return picture;
    }

    public ArtistFavoriteViewModel setPicture(String picture) {
        this.picture = picture;
        return this;
    }
}
