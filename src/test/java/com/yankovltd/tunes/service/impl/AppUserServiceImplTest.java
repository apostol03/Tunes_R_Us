package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.UserEntity;
import com.yankovltd.tunes.model.entity.UserRole;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AppUserServiceImplTest {

    private UserEntity testUser;

    private AppUserServiceImpl serviceToTest;

    @Mock
    private UserRepository mockUserRepository;

    @BeforeEach
    void init() {
        serviceToTest = new AppUserServiceImpl(mockUserRepository);
        UserRole userRole = new UserRole();
        userRole.setRole(UserRoleEnum.USER);

        UserRole moderatorRole = new UserRole();
        moderatorRole.setRole(UserRoleEnum.MODERATOR);

        UserRole adminRole = new UserRole();
        adminRole.setRole(UserRoleEnum.ADMIN);

        testUser = new UserEntity();
        testUser.setUsername("gosho")
                .setEmail("gosho@mail.bg")
                .setPassword("1234")
                .setImageUrl(null)
                .setRoles(List.of(adminRole, moderatorRole, userRole));
    }

    @Test
    void testUserNotFound() {
        assertThrows(
                UsernameNotFoundException.class,
                () -> serviceToTest.loadUserByUsername("hasan")
        );
    }

    @Test
    void testUserFound() {
        when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        var actual = serviceToTest.loadUserByUsername(testUser.getUsername());

        assertEquals(actual.getUsername(), testUser.getUsername());
        String actualRoles = actual.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        String expectedRoles = "ROLE_ADMIN, ROLE_MODERATOR, ROLE_USER";
        assertEquals(expectedRoles, actualRoles);
    }

}