package com.yankovltd.tunes.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "liked_songs")
@NamedEntityGraph(
        name = "liked_songs_entity_graph",
        attributeNodes = {
                @NamedAttributeNode("userEntity"),
                @NamedAttributeNode("song")
        }
)
public class LikedSong extends BaseEntity {

    private UserEntity userEntity;
    private Song song;

    public LikedSong() {
    }

    @OneToOne
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public LikedSong setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
        return this;
    }

    @OneToOne
    public Song getSong() {
        return song;
    }

    public LikedSong setSong(Song song) {
        this.song = song;
        return this;
    }
}
