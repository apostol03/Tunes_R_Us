package com.yankovltd.tunes.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "liked_albums")
@NamedEntityGraph(
        name = "liked_albums_entity_graph",
        attributeNodes = {
                @NamedAttributeNode("album"),
                @NamedAttributeNode("userEntity")
        }
)
public class LikedAlbum extends BaseEntity {

    private UserEntity userEntity;
    private Album album;

    public LikedAlbum() {
    }

    @OneToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LikedAlbum setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @OneToOne
    public Album getAlbum() {
        return album;
    }

    public LikedAlbum setAlbum(Album album) {
        this.album = album;
        return this;
    }
}
