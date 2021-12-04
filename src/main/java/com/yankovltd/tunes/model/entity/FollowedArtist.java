package com.yankovltd.tunes.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "followed_artists")
@NamedEntityGraph(
        name = "followed_artists_entity_graph",
        attributeNodes = {
                @NamedAttributeNode("userEntity"),
                @NamedAttributeNode("artist")
        }
)
public class FollowedArtist extends BaseEntity {

    private UserEntity userEntity;
    private Artist artist;

    public FollowedArtist() {
    }

    @OneToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public FollowedArtist setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @OneToOne
    public Artist getArtist() {
        return artist;
    }

    public FollowedArtist setArtist(Artist artist) {
        this.artist = artist;
        return this;
    }
}
