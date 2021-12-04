package com.yankovltd.tunes.web;

import com.yankovltd.tunes.model.view.AlbumViewModel;
import com.yankovltd.tunes.model.view.ArtistFavoriteViewModel;
import com.yankovltd.tunes.model.view.SongFavoriteViewModel;
import com.yankovltd.tunes.service.FollowedArtistService;
import com.yankovltd.tunes.service.LikedAlbumService;
import com.yankovltd.tunes.service.LikedSongService;
import com.yankovltd.tunes.service.UserService;
import com.yankovltd.tunes.service.impl.AppUser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MyProfileController {

    private final UserService userService;
    private final FollowedArtistService followedArtistService;
    private final LikedAlbumService likedAlbumService;
    private final LikedSongService likedSongService;

    public MyProfileController(UserService userService, FollowedArtistService followedArtistService, LikedAlbumService likedAlbumService, LikedSongService likedSongService) {
        this.userService = userService;
        this.followedArtistService = followedArtistService;
        this.likedAlbumService = likedAlbumService;
        this.likedSongService = likedSongService;
    }

    @GetMapping("/profile")
    public String showProfile(@AuthenticationPrincipal AppUser appUser, Model model) {
        model.addAttribute("user",
                userService.findByUsername(appUser.getUsername()));
        return "my-profile";
    }

    @GetMapping("/profile/favorite-artists")
    public String showFavoriteArtists(@AuthenticationPrincipal AppUser appUser, Model model) {
        List<ArtistFavoriteViewModel> favoriteArtists =
                followedArtistService.getAllFollowedArtistsByUser(appUser.getId());
        model.addAttribute("artists", favoriteArtists);
        return "my-artists";
    }

    @GetMapping("/profile/favorite-albums")
    public String showFavoriteAlbums(@AuthenticationPrincipal AppUser appUser, Model model) {
        List<AlbumViewModel> favoriteAlbums =
                likedAlbumService.getAllLikedAlbumsByUser(appUser.getId());
        model.addAttribute("albums", favoriteAlbums);
        return "my-albums";
    }

    @GetMapping("/profile/favorite-songs")
    public String showFavoriteSongs(@AuthenticationPrincipal AppUser appUser, Model model) {
        List<SongFavoriteViewModel> favoriteSongs =
                likedSongService.getAllLikedSongsByUser(appUser.getId());
        model.addAttribute("songs", favoriteSongs);
        return "my-songs";
    }

    @DeleteMapping("/profile/favorite-songs/{id}")
    public String unlikeSong(@PathVariable Long id, @AuthenticationPrincipal AppUser appUser) {
        likedSongService.unlikeSong(id, appUser);
        return "redirect:/profile/favorite-songs";
    }
}
