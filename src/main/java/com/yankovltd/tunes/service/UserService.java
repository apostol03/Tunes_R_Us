package com.yankovltd.tunes.service;

import com.yankovltd.tunes.model.service.UserRegisterServiceModel;
import com.yankovltd.tunes.model.view.UserPanelViewModel;
import com.yankovltd.tunes.model.view.UserProfileViewModel;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UserService {
    void registerAndLoginUser(UserRegisterServiceModel userServiceModel);

    UserProfileViewModel findByUsername(String username);

    List<UserPanelViewModel> findAllUsersOrderedById();

    void initializeUserAndRoles();

    void deleteUser(Long id);

    UserPanelViewModel findById(Long id);

    boolean isUsernameFree(String username);

    boolean isEmailFree(String email);

    void updateUserRole(Long userId, String role);
}
