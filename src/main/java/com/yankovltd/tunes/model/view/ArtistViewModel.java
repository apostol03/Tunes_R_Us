package com.yankovltd.tunes.model.view;

public class ArtistViewModel {

    private Long id;
    private String name;

    public ArtistViewModel() {
    }

    public String getName() {
        return name;
    }

    public ArtistViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public Long getId() {
        return id;
    }

    public ArtistViewModel setId(Long id) {
        this.id = id;
        return this;
    }
}
