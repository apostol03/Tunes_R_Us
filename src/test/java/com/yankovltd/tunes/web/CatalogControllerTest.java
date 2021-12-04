package com.yankovltd.tunes.web;

import com.yankovltd.tunes.model.entity.*;
import com.yankovltd.tunes.model.entity.enums.GenreEnum;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.repository.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "pesho")
class CatalogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private FollowedArtistRepository followedArtistRepository;

    @Autowired
    private AlbumRepository albumRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private Artist artist;
    private UserEntity userEntity;
    private Album album;

    @BeforeEach
    void setUp() {
//        UserRole userRole = new UserRole().setRole(UserRoleEnum.USER);
//        userRoleRepository.save(userRole);
//        userEntity = new UserEntity()
//                .setUsername("pesho")
//                .setRoles(List.of(userRole))
//                .setEmail("pesho@pesho.bg")
//                .setPassword("1234")
//                .setFirstName("Petar")
//                .setLastName("Petrov");
//        artist = new Artist();
//        artist.setName("bob")
//                .setBio("amazing")
//                .setAlbums(List.of(new Album().setName("bob")))
//                .setPictures(List.of(new Picture().setImageUrl("www")));
//        FollowedArtist followedArtist = new FollowedArtist();
//        followedArtist.setUserEntity(userEntity)
//                .setArtist(artist);
//        artistRepository.save(artist);
//        userRepository.save(userEntity);
//        followedArtistRepository.save(followedArtist);
    }

    @AfterEach
    void tearDown() {
        artistRepository.deleteAll();
    }

    @Test
    void showCollectionShouldDisplayCollection() throws Exception {
        mockMvc
                .perform(get("/collection"))
                .andExpect(status().isOk())
                .andExpect(view().name("catalog"));
    }

    // TODO to fix - also add the tests for details!
//    @Test
//    void followArtistShouldWork() throws Exception {
//        mockMvc
//                .perform(post("/artists/" + artist.getId() + "/details")
//                        .with(csrf()))
//                .andExpect(status().is3xxRedirection());
//    }
}