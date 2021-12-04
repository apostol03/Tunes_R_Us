package com.yankovltd.tunes.web;

import com.yankovltd.tunes.model.view.*;
import com.yankovltd.tunes.service.*;
import com.yankovltd.tunes.service.impl.AppUser;
import com.yankovltd.tunes.web.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/collection")
public class CatalogController {

    private final ArtistService artistService;
    private final AlbumService albumService;
    private final SongService songService;
    private final FollowedArtistService followedArtistService;
    private final LikedAlbumService likedAlbumService;
    private final LikedSongService likedSongService;

    public CatalogController(ArtistService artistService, AlbumService albumService, SongService songService, FollowedArtistService followedArtistService, LikedAlbumService likedAlbumService, LikedSongService likedSongService) {
        this.artistService = artistService;
        this.albumService = albumService;
        this.songService = songService;
        this.followedArtistService = followedArtistService;
        this.likedAlbumService = likedAlbumService;
        this.likedSongService = likedSongService;
    }

    @GetMapping
    public String showCollection() {
        return "catalog";
    }

    @GetMapping("/artists")
    public String showArtists(Model model, @Param("query") String query) {
        return listArtistsByPage(model, 1, query);
    }

    @GetMapping("/artists/page/{pageNumber}")
    public String listArtistsByPage(Model model,
                                    @PathVariable("pageNumber") int currentPage,
                                    @Param("query") String query) {

        Page<ArtistViewModel> page = artistService.showArtists(currentPage, query);
        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<ArtistViewModel> artists = page.getContent();

        boolean hasQuery = false;
        if (query != null && !query.isEmpty()) {
            hasQuery = true;
        }

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("artists", artists);
        model.addAttribute("query", query);
        model.addAttribute("hasQuery", hasQuery);

        return "artists";
    }

    @GetMapping("/albums")
    public String showAlbums(Model model,
                             @Param("query") String query,
                             @RequestParam(defaultValue = "Name",
                                     name = "sortBy") String sortBy) {
        return listAlbumsByPage(model, 1, query, sortBy);
    }

    @GetMapping("/albums/page/{pageNumber}")
    public String listAlbumsByPage(Model model,
                                   @PathVariable("pageNumber") int currentPage,
                                   @Param("query") String query,
                                   @RequestParam(defaultValue = "Name",
                                           name = "sortBy") String sortBy) {
        Page<AlbumViewModel> page = albumService
                .showAlbums(currentPage, query, sortBy);

        long totalItems = page.getTotalElements();
        int totalPages = page.getTotalPages();
        List<AlbumViewModel> albums = page.getContent();

        boolean hasQuery = false;
        if (query != null && !query.isEmpty()) {
            hasQuery = true;
        }

        model.addAttribute("currentPage", currentPage);
        model.addAttribute("totalItems", totalItems);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("albums", albums);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("query", query);
        model.addAttribute("hasQuery", hasQuery);
        return "albums";
    }

    @GetMapping("/artists/{id}/details")
    public String showArtistDetails(@PathVariable Long id,
                                    @AuthenticationPrincipal AppUser appUser, Model model) {
        ArtistDetailsViewModel artist = this.artistService.showArtist(id);

        boolean isFollowed = isFollowed(appUser, artist);

        if (artist == null) {
            throw new ResourceNotFoundException(id);
        }

        List<AlbumViewModel> albums = artist.getAlbums()
                .stream()
                .sorted(Comparator.comparing(AlbumViewModel::getYearReleased).reversed())
                .collect(Collectors.toList());

        model.addAttribute("artist", artist);
        model.addAttribute("albums", albums);
        model.addAttribute("isFollowed", isFollowed);
        return "artist-details";
    }

    @GetMapping("/albums/{id}/details")
    public String showAlbumDetails(@PathVariable Long id,
                                   @AuthenticationPrincipal AppUser appUser, Model model) {
        AlbumDetailsViewModel album = this.albumService.showAlbum(id);
        boolean isLiked = isLiked(appUser, album);

        if (album == null) {
            throw new ResourceNotFoundException(id);
        }

        List<SongViewModel> songs = songService.showSongsOrdered(album.getName());

        model.addAttribute("album", album);
        model.addAttribute("songs", songs);
        model.addAttribute("isContained", isLiked);
        return "album-details";
    }

    @PostMapping("/artists/{id}/details")
    public String followArtist(@PathVariable Long id, @AuthenticationPrincipal AppUser user) {
        followedArtistService.followArtist(id, user);
        return "redirect:/collection/artists/{id}/details";
    }

    @PostMapping("/albums/{id}/details")
    public String likeAlbum(@PathVariable Long id, @AuthenticationPrincipal AppUser user) {
        likedAlbumService.likeAlbum(id, user);
        return "redirect:/collection/albums/{id}/details";
    }

    @PostMapping("/albums/{id}/details/{songId}")
    public String likeSong(@PathVariable Long id, @PathVariable Long songId, @AuthenticationPrincipal AppUser appUser) {
        likedSongService.likeSong(songId, appUser);
        return "redirect:/collection/albums/{id}/details";
    }

    //----------------------------------------------------------------------
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({ResourceNotFoundException.class})
    public ModelAndView catalogErrorHandler(ResourceNotFoundException e) {
        ModelAndView modelAndView = new ModelAndView("resource-not-found");

        modelAndView.addObject("resourceId", e.getResourceId());
        modelAndView.setStatus(HttpStatus.NOT_FOUND);
        return modelAndView;
    }

    private boolean isFollowed(AppUser appUser, ArtistDetailsViewModel artist) {
        if (appUser == null) {
            return false;
        }
        List<ArtistFavoriteViewModel> followedArtists = followedArtistService.getAllFollowedArtistsByUser(appUser.getId());
        ArtistFavoriteViewModel followedArtist = followedArtists.stream()
                .filter(fa -> fa.getName().equals(artist.getName()))
                .findFirst()
                .orElse(null);

        return followedArtist != null;
    }

    private boolean isLiked(AppUser appUser, AlbumDetailsViewModel album) {
        if (appUser == null) {
            return false;
        }
        List<AlbumViewModel> likedAlbums = likedAlbumService.getAllLikedAlbumsByUser(appUser.getId());
        AlbumViewModel likedAlbum = likedAlbums.stream()
                .filter(la -> la.getName().equals(album.getName()))
                .findFirst()
                .orElse(null);

        return likedAlbum != null;
    }
}
