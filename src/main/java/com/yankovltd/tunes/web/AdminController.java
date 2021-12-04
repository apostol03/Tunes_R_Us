package com.yankovltd.tunes.web;

import com.yankovltd.tunes.service.StatsService;
import com.yankovltd.tunes.service.UserService;
import com.yankovltd.tunes.service.impl.AppUser;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminController {

    private final UserService userService;
    private final StatsService statsService;

    public AdminController(UserService userService, StatsService statsService) {
        this.userService = userService;
        this.statsService = statsService;
    }

    @GetMapping("/status-panel")
    public String statusPanel(Model model, @AuthenticationPrincipal AppUser appUser) {
        model.addAttribute("users", userService.findAllUsersOrderedById());
        model.addAttribute("currentId", appUser.getId());
        return "status-panel";
    }

    @GetMapping("/status-panel/stats")
    public String showStatistics(Model model) {
        model.addAttribute("albumVisits", statsService.getAlbumVisits());
        model.addAttribute("artistVisits", statsService.getArtistVisits());
        return "stats";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/edit/{userId}")
    public String editUser(@PathVariable Long userId,
                           @RequestParam(name = "select-role") String role) {
        userService.updateUserRole(userId, role);
        return "redirect:/status-panel";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/status-panel";
    }
}
