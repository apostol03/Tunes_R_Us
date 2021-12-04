package com.yankovltd.tunes.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class ChartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showChartsWorks() throws Exception {
        mockMvc
                .perform(get("/charts"))
                .andExpect(status().isOk())
                .andExpect(view().name("chart"));
    }

    @Test
    void showArtistsChartWorks() throws Exception {
        mockMvc
                .perform(get("/charts/artists-chart"))
                .andExpect(status().isOk())
                .andExpect(view().name("artists-chart"));
    }

    @Test
    void showSongsChartWorks() throws Exception {
        mockMvc
                .perform(get("/charts/songs-chart"))
                .andExpect(status().isOk())
                .andExpect(view().name("songs-chart"));
    }
}