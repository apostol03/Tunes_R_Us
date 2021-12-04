package com.yankovltd.tunes.web;

import com.yankovltd.tunes.model.entity.UserEntity;
import com.yankovltd.tunes.model.entity.UserRole;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.repository.UserRoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "pesho")
class MyProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

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
    }

    @Test
    @WithUserDetails("pesho")
    void showProfileShouldVisualizeUserProfile() throws Exception {
        mockMvc
                .perform(get("/profile"))
                .andExpect(status().isOk())
                .andExpect(view().name("my-profile"))
                .andExpect(model().attributeExists("user"));
    }

//    @Test
//    @WithUserDetails("pesho")
//    void showFavoriteArtistsShouldWork() throws Exception {
//        mockMvc
//                .perform(get("/profile/favorite-artists"))
//                .andExpect(status().isOk())
//                .andExpect(view().name("my-artists"))
//                .andExpect(model().attributeExists("artists"));
//    }

}