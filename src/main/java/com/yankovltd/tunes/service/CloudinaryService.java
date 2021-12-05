package com.yankovltd.tunes.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CloudinaryService {

    String uploadProfilePicture(MultipartFile picture) throws IOException;
}
