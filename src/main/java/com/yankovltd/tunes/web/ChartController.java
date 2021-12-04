package com.yankovltd.tunes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/charts")
public class ChartController {

    @GetMapping
    public String showCharts() {
        return "chart";
    }

    @GetMapping("/artists-chart")
    public String showArtistsChart() {
        return "artists-chart";
    }

    @GetMapping("/songs-chart")
    public String showSongsChart() {
        return "songs-chart";
    }
}
