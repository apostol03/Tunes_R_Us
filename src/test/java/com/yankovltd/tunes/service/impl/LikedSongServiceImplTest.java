package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.*;
import com.yankovltd.tunes.model.entity.enums.GenreEnum;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.model.view.SongFavoriteViewModel;
import com.yankovltd.tunes.repository.LikedSongRepository;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.service.LikedSongService;
import com.yankovltd.tunes.service.SongService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WithMockUser(username = "pesho", roles = {"USER", "MODERATOR", "ADMIN"})
@ExtendWith(MockitoExtension.class)
class LikedSongServiceImplTest {

    private LikedSongService serviceToTest;
    private UserEntity userEntity;
    private LikedSong likedSong;

    @Mock
    private UserRepository userRepository;

    @Mock
    private LikedSongRepository likedSongRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private SongService songService;

    @BeforeEach
    void setUp() {
        serviceToTest = new LikedSongServiceImpl(likedSongRepository, modelMapper, userRepository, songService);
        Song song = new Song();
        Artist artist = new Artist().setName("JK");
        Album album = new Album()
                .setName("Azis")
                .setArtist(artist)
                .setGenre(GenreEnum.POP)
                .setPicture(new Picture().setImageUrl("1"))
                .setYearReleased(1);
        song
                .setName("redrum")
                .setArtist(artist)
                .setAlbum(album);
        userEntity = new UserEntity();
        userEntity
                .setUsername("pesho")
                .setRoles(List.of(new UserRole().setRole(UserRoleEnum.USER)))
                .setEmail("pesho@pesho.bg")
                .setFirstName("Petar")
                .setLastName("Petrov");
        likedSong = new LikedSong();
        likedSong.setUserEntity(userEntity);
        likedSong.setSong(song);
    }

    @Test
    void getAllLikedSongsByUserShouldWork() {
        when(likedSongRepository.findAllByUserEntityId(userEntity.getId()))
                .thenReturn(List.of(likedSong));

        var views = serviceToTest.getAllLikedSongsByUser(userEntity.getId());

        SongFavoriteViewModel actual = views.get(0);
        assertEquals(1, views.size());
        assertEquals(likedSong.getSong().getName(), actual.getName());
        assertEquals(likedSong.getSong().getArtist().getName(), actual.getArtist());
    }
}