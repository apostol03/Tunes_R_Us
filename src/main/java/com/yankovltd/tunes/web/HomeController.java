package com.yankovltd.tunes.web;

import com.yankovltd.tunes.service.AlbumService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final AlbumService albumService;

    public HomeController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("albums", albumService.showHomeAlbums());
        return "index";
    }

    @GetMapping("/about")
    public String showAbout() {
        return "about";
    }
}
