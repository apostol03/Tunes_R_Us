package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.*;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.model.view.ArtistFavoriteViewModel;
import com.yankovltd.tunes.repository.FollowedArtistRepository;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.service.ArtistService;
import com.yankovltd.tunes.service.FollowedArtistService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@WithMockUser(username = "pesho", roles = {"USER", "ADMIN", "MODERATOR"})
@ExtendWith(MockitoExtension.class)
class FollowedArtistServiceImplTest {

    private FollowedArtistService serviceToTest;
    private UserEntity userEntity;
    private FollowedArtist followedArtist;

    @Mock
    private ArtistService artistService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private FollowedArtistRepository followedArtistRepository;

    @BeforeEach
    void setUp() {
        serviceToTest = new FollowedArtistServiceImpl(artistService, userRepository, followedArtistRepository);
        Artist artist = new Artist();
        artist.setName("acdc")
                .setPictures(List.of(new Picture().setImageUrl("b"), new Picture().setImageUrl("a"),
                        new Picture().setImageUrl("c")))
                .setAlbums(List.of(new Album().setName("bla")))
                .setBio("uu");
        userEntity = new UserEntity();
        userEntity.setUsername("pesho")
                .setRoles(List.of(new UserRole().setRole(UserRoleEnum.USER)))
                .setEmail("pesho@pesho.bg")
                .setFirstName("Petar")
                .setLastName("Petrov");
        followedArtist = new FollowedArtist();
        followedArtist.setArtist(artist);
        followedArtist.setUserEntity(userEntity);
    }

    @Test
    void getAllFollowedArtistsByUserShouldWork() {
        when(followedArtistRepository.findAllByUserEntityId(userEntity.getId()))
                .thenReturn(List.of(followedArtist));

        List<ArtistFavoriteViewModel> actualViews = serviceToTest
                .getAllFollowedArtistsByUser(userEntity.getId());

        assertEquals(1, actualViews.size());
        ArtistFavoriteViewModel actualView = actualViews.get(0);

        assertEquals(followedArtist.getArtist().getName(), actualView.getName());
        assertEquals(followedArtist.getArtist().getPictures().get(2).getImageUrl(), actualView.getPicture());
    }

    @Test
    void deleteUserFollowedArtistsShouldWork() {
        when(followedArtistRepository.findByUserEntityId(userEntity.getId()))
                .thenReturn(List.of(followedArtist));

        serviceToTest.deleteUserFollowedArtists(userEntity.getId());
        assertEquals(0, followedArtistRepository.count());
    }
}