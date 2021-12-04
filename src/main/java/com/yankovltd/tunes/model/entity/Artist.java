package com.yankovltd.tunes.model.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "artists")
@NamedEntityGraph(
        name = "artists_entity_graph",
        attributeNodes = {
                @NamedAttributeNode("pictures")
        }
)
public class Artist extends BaseEntity {

    private String name;
    private String bio;

    private List<Album> albums;
    private List<Picture> pictures;

    public Artist() {
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public Artist setName(String name) {
        this.name = name;
        return this;
    }

    @Column(nullable = false, columnDefinition = "TEXT")
    public String getBio() {
        return bio;
    }

    public Artist setBio(String bio) {
        this.bio = bio;
        return this;
    }

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    public List<Picture> getPictures() {
        return pictures;
    }

    public Artist setPictures(List<Picture> pictures) {
        this.pictures = pictures;
        return this;
    }

    @OneToMany(mappedBy = "artist", fetch = FetchType.LAZY)
    public List<Album> getAlbums() {
        return albums;
    }

    public Artist setAlbums(List<Album> albums) {
        this.albums = albums;
        return this;
    }
}
