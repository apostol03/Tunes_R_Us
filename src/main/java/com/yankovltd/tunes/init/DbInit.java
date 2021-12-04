package com.yankovltd.tunes.init;

import com.yankovltd.tunes.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DbInit implements CommandLineRunner {

    private final UserService userService;

    public DbInit(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initializeUserAndRoles();
    }
}
