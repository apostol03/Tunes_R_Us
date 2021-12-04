package com.yankovltd.tunes.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "pictures")
public class Picture extends BaseEntity {

    private String name;
    private String imageUrl;

    private Artist artist;

    public Picture() {
    }

    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public Picture setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false)
    public String getImageUrl() {
        return imageUrl;
    }

    public Picture setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @ManyToOne
    public Artist getArtist() {
        return artist;
    }

    public Picture setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }

}
