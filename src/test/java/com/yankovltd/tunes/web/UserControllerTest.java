package com.yankovltd.tunes.web;

import com.yankovltd.tunes.model.entity.UserEntity;
import com.yankovltd.tunes.model.entity.UserRole;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.repository.UserRepository;
import com.yankovltd.tunes.repository.UserRoleRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import javax.annotation.PostConstruct;
import java.util.Optional;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void testOpenRegisterForm() throws Exception {
        mockMvc
                .perform(get("/users/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("register"));
    }

    // works when ran alone or in class
    @Test
    void testRegisterUser() throws Exception {
        mockMvc
                .perform(post("/users/register")
                        .param("firstName", "Gosho")
                        .param("lastName", "Georgiev")
                        .param("email", "gosho@mail.bg")
                        .param("username", "gosho")
                        .param("password", "1234")
                        .param("confirmPassword", "1234")
                        .with(csrf())
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                )
                .andExpect(status().is3xxRedirection());

        assertEquals(1, userRepository.count());

        Optional<UserEntity> newlyCreatedUserOpt = userRepository.findByUsername("gosho");
        assertTrue(newlyCreatedUserOpt.isPresent());

        UserEntity user = newlyCreatedUserOpt.get();
        assertEquals("gosho@mail.bg", user.getEmail());
        assertEquals("gosho", user.getUsername());
        assertEquals("Gosho", user.getFirstName());
        assertEquals("Georgiev", user.getLastName());
    }

    @Test
    void showLoginWithoutAuthenticatedUserShouldReturnLoginPage() throws Exception {
        mockMvc
                .perform(get("/users/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "pesho")
    void showLoginWithAuthenticatedUserShouldRedirectToHome() throws Exception {
        mockMvc
                .perform(get("/users/login"))
                .andExpect(status().is3xxRedirection());
    }
}