package com.yankovltd.tunes.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/quiz")
public class QuizController {

    @GetMapping
    public String showQuiz() {
        return "quiz-home";
    }

    @GetMapping("/1")
    public String showFirstQuiz() {
        return "quiz";
    }

    @GetMapping("/2")
    public String showSecondQuiz() {
        return "quiz";
    }

    @GetMapping("/3")
    public String showThirdQuiz() {
        return "quiz";
    }

    @GetMapping("/4")
    public String showFourthQuiz() {
        return "quiz";
    }

    @GetMapping("/5")
    public String showFifthQuiz() {
        return "quiz";
    }

    @GetMapping("/6")
    public String showLastQuiz() {
        return "quiz";
    }

}
