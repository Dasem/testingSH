package com.shem.testing.controllers;

import com.shem.testing.Question;
import com.shem.testing.services.FilterService;
import com.shem.testing.services.XlsxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private static int requestCounter = 0;

    @Autowired
    XlsxParser xlsxParser;

    @Autowired
    FilterService filterService;

    @GetMapping("/")
    public String regPage(Model model) {
        model.addAttribute("count", requestCounter++);
        return "registration";
    }

    @PostMapping("/themes")
    public String themes(Model model,
                         @RequestParam(name = "inputFIO") String fio,
                         @RequestParam(name = "inputGroup") Integer group,
                         @RequestParam(name = "inputCourse") Integer course,
                         @RequestParam(name = "inputQuestionsCount") Integer questionsCount) {
        model.addAttribute(fio);
        model.addAttribute(group);
        model.addAttribute(course);
        model.addAttribute(questionsCount);
        model.addAttribute("themes", xlsxParser.getThemes());
        return "themes";
    }

    @PostMapping("/test")
    public String test(Model model,
                       @RequestParam(name = "inputFIO") String fio,
                       @RequestParam(name = "inputGroup") Integer group,
                       @RequestParam(name = "inputCourse") Integer course,
                       @RequestParam(name = "inputQuestionsCount") Integer questionsCount,
                       @RequestParam(name = "themes") String themes) {
        model.addAttribute(fio);
        model.addAttribute(group);
        model.addAttribute(course);
        model.addAttribute(questionsCount);
        //List<Question> filteredQuestions = filterService.filterQuestion(themes); //TODO: заполнять отфильтрованными вопросами
        //model.addAttribute("questions", filteredQuestions);
        return "test";
    }
}
