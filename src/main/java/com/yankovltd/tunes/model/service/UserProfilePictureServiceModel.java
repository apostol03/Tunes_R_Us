package com.yankovltd.tunes.model.service;

import org.springframework.web.multipart.MultipartFile;

public class UserProfilePictureServiceModel {

    private MultipartFile picture;
    private String username;

    public UserProfilePictureServiceModel() {
    }

    public MultipartFile getPicture() {
        return picture;
    }

    public UserProfilePictureServiceModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public UserProfilePictureServiceModel setUsername(String username) {
        this.username = username;
        return this;
    }
}
