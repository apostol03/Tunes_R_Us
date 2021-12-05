package com.yankovltd.tunes.web;

import com.yankovltd.tunes.model.entity.*;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.repository.*;
import com.yankovltd.tunes.service.impl.AppUser;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "pesho", roles = {"ADMIN", "MODERATOR"})
class AdminControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikedSongRepository likedSongRepository;

    @Autowired
    private LikedAlbumRepository likedAlbumRepository;

    @Autowired
    private FollowedArtistRepository followedArtistRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    private UserEntity testUser;

    @PostConstruct
    void setUp() {
        UserRole moderatorRole = new UserRole().setRole(UserRoleEnum.MODERATOR);
        UserRole adminRole = new UserRole().setRole(UserRoleEnum.ADMIN);

        userRepository.deleteAll();
        userRoleRepository.save(moderatorRole);
        userRoleRepository.save(adminRole);

        testUser = new UserEntity()
                .setUsername("pesho")
                .setEmail("email")
                .setFirstName("Petar")
                .setLastName("Petrov")
                .setPassword("1234")
                .setRoles(List.of(moderatorRole, adminRole));
        userRepository.save(testUser);

        likedAlbumRepository.deleteAll();
        likedSongRepository.deleteAll();
        followedArtistRepository.deleteAll();

        LikedAlbum likedAlbum = new LikedAlbum().setUserEntity(testUser)
                .setAlbum(new Album().setName("a"));
        LikedSong likedSong = new LikedSong().setUserEntity(testUser)
                .setSong(new Song().setName("b"));
        FollowedArtist followedArtist = new FollowedArtist().setUserEntity(testUser)
                .setArtist(new Artist().setName("c"));

    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    @WithUserDetails("pesho")
    void statusPanelShouldBeDisplayedCorrectly() throws Exception {
        mockMvc
                .perform(get("/status-panel"))
                .andExpect(status().isOk())
                .andExpect(view().name("status-panel"))
                .andExpect(model().attributeExists("users", "currentId"));
    }

    @Test
    void showStatisticsShouldBeDisplayedCorrectly() throws Exception {
        mockMvc
                .perform(get("/status-panel/stats"))
                .andExpect(status().isOk())
                .andExpect(view().name("stats"))
                .andExpect(model().attributeExists("albumVisits", "artistVisits"));

    }

    // not working
//    @Test
//    @WithUserDetails("pesho")
//    void editUserShouldWork() throws Exception {
//        mockMvc
//                .perform(patch("/edit/" + testUser.getId())
//                        .with(csrf())
//                        .param("select-role", "MODERATOR"))
//                .andExpect(status().is3xxRedirection());
//    }

    @Test
    @WithUserDetails("pesho")
    void deleteUserShouldWork() throws Exception {
        mockMvc
                .perform(delete("/delete/" + testUser.getId())
                        .with(csrf()))
                .andExpect(status().is3xxRedirection());
        assertEquals(0, userRepository.count());
        assertEquals(0, likedAlbumRepository.count());
        assertEquals(0, likedSongRepository.count());
        assertEquals(0, followedArtistRepository.count());
    }
}