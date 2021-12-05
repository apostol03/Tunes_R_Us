package com.yankovltd.tunes.service.impl;

import com.yankovltd.tunes.model.entity.*;
import com.yankovltd.tunes.model.entity.enums.UserRoleEnum;
import com.yankovltd.tunes.model.service.UserProfilePictureServiceModel;
import com.yankovltd.tunes.model.service.UserRegisterServiceModel;
import com.yankovltd.tunes.model.view.UserPanelViewModel;
import com.yankovltd.tunes.model.view.UserProfileViewModel;
import com.yankovltd.tunes.repository.*;
import com.yankovltd.tunes.service.*;
import com.yankovltd.tunes.web.exception.ResourceNotFoundException;
import com.yankovltd.tunes.web.exception.UserNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppUserServiceImpl appUserService;
    private final LikedAlbumService likedAlbumService;
    private final LikedSongService likedSongService;
    private final FollowedArtistService followedArtistService;
    private final CloudinaryService cloudinaryService;

    public UserServiceImpl(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, AppUserServiceImpl appUserService, LikedAlbumService likedAlbumService, LikedSongService likedSongService, FollowedArtistService followedArtistService, CloudinaryService cloudinaryService) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
        this.likedAlbumService = likedAlbumService;
        this.likedSongService = likedSongService;
        this.followedArtistService = followedArtistService;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void initializeUserAndRoles() {
        initRoles();
        initUser();
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRole userRole = new UserRole();
            userRole.setRole(UserRoleEnum.USER);

            UserRole moderatorRole = new UserRole();
            moderatorRole.setRole(UserRoleEnum.MODERATOR);

            UserRole adminRole = new UserRole();
            adminRole.setRole(UserRoleEnum.ADMIN);

            userRoleRepository.saveAll(List.of(userRole, moderatorRole, adminRole));
        }
    }

    private void initUser() {
        if (userRepository.count() == 0) {
            UserRole userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
            UserRole moderatorRole = userRoleRepository.findByRole(UserRoleEnum.MODERATOR);
            UserRole adminRole = userRoleRepository.findByRole(UserRoleEnum.ADMIN);

            UserEntity admin = new UserEntity();

            admin.setFirstName("Apostol")
                    .setLastName("Yankov")
                    .setPassword(passwordEncoder.encode("admin"))
                    .setEmail("apostol@mail.bg")
                    .setUsername("apostol")
                    .setImageUrl(null)
                    .setRoles(List.of(userRole, moderatorRole, adminRole));

            userRepository.save(admin);
        }
    }

    @Transactional
    @Override
    public void registerAndLoginUser(UserRegisterServiceModel userServiceModel) {

        UserRole userRole = userRoleRepository.findByRole(UserRoleEnum.USER);
        UserEntity newUser = new UserEntity();

        newUser
                .setUsername(userServiceModel.getUsername())
                .setEmail(userServiceModel.getEmail())
                .setFirstName(userServiceModel.getFirstName())
                .setLastName(userServiceModel.getLastName())
                .setPassword(passwordEncoder.encode(userServiceModel.getPassword()))
                .setImageUrl(null)
                .setRoles(List.of(userRole));

        newUser = userRepository.save(newUser);

        UserDetails principal = appUserService.loadUserByUsername(newUser.getUsername());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                principal,
                newUser.getPassword(),
                principal.getAuthorities()
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    @Override
    public void deleteUser(Long id) {
        likedAlbumService.deleteUserLikedAlbums(id);
        likedSongService.deleteUserLikedSongs(id);
        followedArtistService.deleteUserFollowedArtists(id);

        userRepository.deleteById(id);
    }

    @Override
    public void updateUserRole(Long userId, String role) {
        UserEntity userToUpdate = userRepository.findById(userId).orElse(null);

        if (userToUpdate == null) {
            throw new ResourceNotFoundException(userId);
        }

        UserRole userRole = userRoleRepository.findByRole(UserRoleEnum.valueOf(role));

        userToUpdate = userToUpdate.setRoles(List.of(userRole));

        userRepository.save(userToUpdate);
    }

    @Override
    public boolean isUsernameFree(String username) {
        return userRepository.findByUsernameIgnoreCase(username).isEmpty();
    }

    @Override
    public boolean isEmailFree(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    // PROFILE METHOD
    @Transactional
    @Override
    public UserProfileViewModel findByUsername(String username) {
        return userRepository.findByUsername(username)
                .stream()
                .map(user -> {
                    UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
                    userProfileViewModel.setId(user.getId());
                    userProfileViewModel.setFirstName(user.getFirstName())
                            .setLastName(user.getLastName());

                    String role = setCorrectRole(user);

                    userProfileViewModel.setImageUrl(user.getImageUrl());
                    userProfileViewModel.setRole(role);
                    userProfileViewModel.setQuizPoints(0);
                    return userProfileViewModel;
                })
                .findFirst()
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void uploadProfilePicture(UserProfilePictureServiceModel userProfilePictureServiceModel) {
        UserEntity userEntity = userRepository
                .findByUsername(userProfilePictureServiceModel.getUsername())
                .orElseThrow(() -> new UserNotFoundException("User not found (" + userProfilePictureServiceModel.getUsername() + ")"));
        try {
            String imageUrl = cloudinaryService.uploadProfilePicture(userProfilePictureServiceModel.getPicture());
            userEntity.setImageUrl(imageUrl);
            userRepository.save(userEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public UserPanelViewModel findById(Long id) {
        return userRepository.findById(id)
                .stream()
                .map(mapToUserView())
                .findFirst()
                .orElse(null);
    }

    @Transactional
    @Override
    public List<UserPanelViewModel> findAllUsersOrderedById() {
        return userRepository.findAllByOrderById()
                .stream()
                .map(mapToUserView())
                .collect(Collectors.toList());
    }

    private Function<UserEntity, UserPanelViewModel> mapToUserView() {
        return userEntity -> {
            UserPanelViewModel userPanelViewModel = new UserPanelViewModel();
            userPanelViewModel.setId(userEntity.getId());
            userPanelViewModel.setUsername(userEntity.getUsername());

            String role = setCorrectRole(userEntity);

            userPanelViewModel.setRole(role);
            return userPanelViewModel;
        };
    }

    private String setCorrectRole(UserEntity userEntity) {
        List<UserRoleEnum> roles = userEntity.getRoles()
                .stream()
                .map(UserRole::getRole)
                .collect(Collectors.toList());

        String role;

        if (roles.contains(UserRoleEnum.valueOf("USER")) &&
                !roles.contains(UserRoleEnum.valueOf("ADMIN")) &&
                !roles.contains(UserRoleEnum.valueOf("MODERATOR"))) {
            role = "USER";
        } else if (!roles.contains(UserRoleEnum.valueOf("USER")) &&
                roles.contains(UserRoleEnum.valueOf("ADMIN")) &&
                !roles.contains(UserRoleEnum.valueOf("MODERATOR"))) {
            role = "ADMIN";
        } else if (!roles.contains(UserRoleEnum.valueOf("USER")) &&
                !roles.contains(UserRoleEnum.valueOf("ADMIN")) &&
                roles.contains(UserRoleEnum.valueOf("MODERATOR"))) {
            role = "MODERATOR";
        } else if (roles.size() == 2) {
            role = "MODERATOR";
        } else {
            role = "ADMIN";
        }
        return role;
    }

}
