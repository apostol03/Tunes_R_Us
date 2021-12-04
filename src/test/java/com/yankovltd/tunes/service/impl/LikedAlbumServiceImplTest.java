package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.*;
import com.yankovltd.tunes.model.entity.enums.GenreEnum;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.repository.LikedAlbumRepository;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.service.AlbumService;
import com.yankovltd.tunes.service.LikedAlbumService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WithMockUser(username = "pesho", roles = {"USER", "MODERATOR", "ADMIN"})
@ExtendWith(MockitoExtension.class)
class LikedAlbumServiceImplTest {

    private LikedAlbumService serviceToTest;
    private UserEntity userEntity;
    private LikedAlbum likedAlbum;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AlbumService albumService;

    @Mock
    private LikedAlbumRepository likedAlbumRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new LikedAlbumServiceImpl(userRepository, albumService, likedAlbumRepository);
        Album album = new Album();
        album
                .setName("Azis")
                .setArtist(new Artist().setName("Azis"))
                .setGenre(GenreEnum.POP)
                .setPicture(new Picture().setImageUrl("1"))
                .setYearReleased(1);

        userEntity = new UserEntity();
        userEntity
                .setUsername("pesho")
                .setRoles(List.of(new UserRole().setRole(UserRoleEnum.USER)))
                .setEmail("pesho@pesho.bg")
                .setFirstName("Petar")
                .setLastName("Petrov");
        likedAlbum = new LikedAlbum();
        likedAlbum
                .setUserEntity(userEntity)
                .setAlbum(album);
    }

    @Test
    void getAllLikedAlbumsByUserShouldWork() {
        when(likedAlbumRepository.findAllByUserEntityId(userEntity.getId()))
                .thenReturn(List.of(likedAlbum));

        var views = serviceToTest.getAllLikedAlbumsByUser(userEntity.getId());

        AlbumViewModel actual = views.get(0);
        assertEquals(1, views.size());
        assertEquals(likedAlbum.getAlbum().getName(), actual.getName());
        assertEquals(likedAlbum.getAlbum().getGenre().toString(), actual.getGenre());
        assertEquals(likedAlbum.getAlbum().getArtist().getName(), actual.getArtist());
        assertEquals(likedAlbum.getAlbum().getYearReleased(), actual.getYearReleased());
        assertEquals(likedAlbum.getAlbum().getPicture().getImageUrl(), actual.getPicture());
    }
}