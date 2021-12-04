package com.yankovltd.tunes.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WithMockUser("gosho")
@SpringBootTest
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testQuizHomeIsOpened() throws Exception {
        mockMvc
                .perform(get("/quiz"))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz-home"));
    }

    @Test
    void testQuiz1IsOpened() throws Exception {
        mockMvc
                .perform(get("/quiz/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz"));
    }

    @Test
    void testQuiz2IsOpened() throws Exception {
        mockMvc
                .perform(get("/quiz/2"))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz"));
    }

    @Test
    void testQuiz3IsOpened() throws Exception {
        mockMvc
                .perform(get("/quiz/3"))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz"));
    }

    @Test
    void testQuiz4IsOpened() throws Exception {
        mockMvc
                .perform(get("/quiz/4"))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz"));
    }

    @Test
    void testQuiz5IsOpened() throws Exception {
        mockMvc
                .perform(get("/quiz/5"))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz"));
    }

    @Test
    void testQuiz6IsOpened() throws Exception {
        mockMvc
                .perform(get("/quiz/6"))
                .andExpect(status().isOk())
                .andExpect(view().name("quiz"));
    }
}