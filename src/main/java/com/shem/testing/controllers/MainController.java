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
        model.addAttribute("questionsSize", xlsxParser.getQuestions().size());
        return "registration";
    }

    @PostMapping("/theme")
    public String themes(Model model,
                         @RequestParam(name = "inputFIO") String fio,
                         @RequestParam(name = "inputGroup") String group,
                         @RequestParam(name = "inputCourse") Integer course,
                         @RequestParam(name = "inputQuestionsCount") Integer questionsCount) {
        model.addAttribute("inputFIO", fio);
        model.addAttribute("inputGroup", group);
        model.addAttribute("inputCourse", course);
        model.addAttribute("inputQuestionsCount", questionsCount);
        model.addAttribute("themes", xlsxParser.getThemes());
        return "theme";
    }

    @PostMapping("/test")
    public String test(Model model,
                       @RequestParam(name = "inputFIO") String fio,
                       @RequestParam(name = "inputGroup") String group,
                       @RequestParam(name = "inputCourse") Integer course,
                       @RequestParam(name = "inputQuestionsCount") Integer questionsCount,
                       @RequestParam(name = "themes") List<String> themes) {
        model.addAttribute("inputFIO", fio);
        model.addAttribute("inputGroup", group);
        model.addAttribute("inputCourse", course);
        model.addAttribute("inputQuestionsCount", questionsCount);
        List<Question> filteredByThemesQuestions = filterService.filterQuestionByThemes(themes);
        List<Question> filteredByAllQuestions = filterService.filterQuestionByCount(filteredByThemesQuestions, questionsCount);
        model.addAttribute("questions", filteredByAllQuestions);
        return "test";
    }
}
