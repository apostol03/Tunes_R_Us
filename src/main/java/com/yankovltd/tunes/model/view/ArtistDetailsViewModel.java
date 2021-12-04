package com.yankovltd.tunes.model.view;

import java.util.List;

public class ArtistDetailsViewModel {

    private String name;
    private String bio;
    private String artistPicture;
    private String bioPicture;
    private List<AlbumViewModel> albums;

    public ArtistDetailsViewModel() {
    }

    public String getName() {
        return name;
    }

    public ArtistDetailsViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public String getBio() {
        return bio;
    }

    public ArtistDetailsViewModel setBio(String bio) {
        this.bio = bio;
        return this;
    }

    public List<AlbumViewModel> getAlbums() {
        return albums;
    }

    public ArtistDetailsViewModel setAlbums(List<AlbumViewModel> albums) {
        this.albums = albums;
        return this;
    }

    public String getArtistPicture() {
        return artistPicture;
    }

    public ArtistDetailsViewModel setArtistPicture(String artistPicture) {
        this.artistPicture = artistPicture;
        return this;
    }

    public String getBioPicture() {
        return bioPicture;
    }

    public ArtistDetailsViewModel setBioPicture(String bioPicture) {
        this.bioPicture = bioPicture;
        return this;
    }
}
