package com.yankovltd.tunes.config;

import com.cloudinary.Cloudinary;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import java.util.Map;

@Configuration
public class ApplicationBeanConfiguration {

    private final CloudinaryConfiguration cloudinaryConfiguration;

    public ApplicationBeanConfiguration(CloudinaryConfiguration cloudinaryConfiguration) {
        this.cloudinaryConfiguration = cloudinaryConfiguration;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Pbkdf2PasswordEncoder();
    }

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(
                Map.of(
                        "cloud_name", cloudinaryConfiguration.getCloudName(),
                        "api_key", cloudinaryConfiguration.getApiKey(),
                        "api_secret", cloudinaryConfiguration.getApiSecret()
                )
        );
    }
}
