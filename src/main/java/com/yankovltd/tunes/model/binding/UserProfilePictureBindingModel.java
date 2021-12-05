package com.yankovltd.tunes.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class UserProfilePictureBindingModel {

    private MultipartFile picture;

    public MultipartFile getPicture() {
        return picture;
    }

    public UserProfilePictureBindingModel setPicture(MultipartFile picture) {
        this.picture = picture;
        return this;
    }
}
