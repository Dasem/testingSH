package com.shem.testing.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    private static int requestCounter = 0;

    @GetMapping("/")
    public String regPage(Model model) {
        model.addAttribute("count", requestCounter++);
        return "registration";
    }

    @PostMapping("/test")
    public String test(Model model,
                       @RequestParam(name = "inputFIO") String fio,
                       @RequestParam(name = "inputGroup") Integer group,
                       @RequestParam(name = "inputCourse") Integer course,
                       @RequestParam(name = "inputQuestionsCount") Integer questionsCount) {
        model.addAttribute(fio);
        model.addAttribute(group);
        model.addAttribute(course);
        model.addAttribute(questionsCount);

        return "test";
    }
}
