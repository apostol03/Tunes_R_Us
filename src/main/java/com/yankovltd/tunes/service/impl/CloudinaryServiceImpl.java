package com.yankovltd.tunes.service.impl;

import com.cloudinary.Cloudinary;
import com.yankovltd.tunes.model.entity.Picture;
import com.yankovltd.tunes.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private static final String TEMP_FILE = "temp_file";
    private static final String URL = "url";

    private final Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @Override
    public String uploadProfilePicture(MultipartFile picture) throws IOException {
        File tempFile = File.createTempFile(TEMP_FILE, picture.getOriginalFilename());
        picture.transferTo(tempFile);

        try {
            @SuppressWarnings("unchecked")
            Map<String, String> uploadResult = cloudinary
                    .uploader()
                    .upload(tempFile, Map.of());

            return uploadResult.getOrDefault(URL, "https://static.wixstatic.com/media/75f6cd_ec75ac4441294fc0b385b008b445d3b3~mv2.png/v1/fill/w_300,h_300,al_c,q_85,usm_0.66_1.00_0.01/default-profile.webp");

        } finally {
            tempFile.delete();
        }

    }
}
