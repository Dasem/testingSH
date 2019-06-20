package com.shem.testing.controllers;

import com.shem.testing.Answer;
import com.shem.testing.AnswersHolder;
import com.shem.testing.Question;
import com.shem.testing.services.FilterService;
import com.shem.testing.services.XlsxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController extends BaseController {

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
    public String themes(Model model, HttpSession session,
                         @RequestParam(name = "inputFIO") String fio,
                         @RequestParam(name = "inputGroup") String group,
                         @RequestParam(name = "inputCourse") Integer course,
                         @RequestParam(name = "inputQuestionsCount") Integer questionsCount) {
        session.setAttribute("inputFIO", fio);
        session.setAttribute("inputGroup", group);
        session.setAttribute("inputCourse", course);

        model.addAttribute("inputQuestionsCount", questionsCount);
        model.addAttribute("themes", xlsxParser.getThemes());
        return "theme";
    }

    @PostMapping("/test")
    public String test(Model model, HttpSession session,
                       @RequestParam(name = "inputQuestionsCount") Integer questionsCount,
                       @RequestParam(name = "themes") List<String> themes) {
        model.addAttribute("inputQuestionsCount", questionsCount);
        List<Question> filteredByThemesQuestions = filterService.filterQuestionByThemes(themes);
        List<Question> filteredByAllQuestions = filterService.filterQuestionByCount(filteredByThemesQuestions, questionsCount);
        model.addAttribute("questions", filteredByAllQuestions);
        session.setAttribute("questions", filteredByAllQuestions);
        return "test";
    }

    @SuppressWarnings("unchecked")
    @PostMapping("/result")
    public String themes(Model model, HttpSession session, @RequestParam(name = "answers") List<String> answers) {
        AnswersHolder answersHolder = new AnswersHolder();
        List<Answer> oAnswers = new ArrayList<>();
        for (String answer : answers) {
            String[] splitted = answer.split("_");
            oAnswers.add(new Answer(splitted[1], Integer.parseInt(splitted[0])));
        }
        answersHolder.setAnswers(oAnswers);
        List<Question> questions = (List<Question>) session.getAttribute("questions");

        List<String> tokens = new ArrayList<>();
        for (Question question : questions) {
            tokens.add(question.getToken());
        }
        answersHolder.setTokens(tokens);
        answersHolder.setUserInfo(
                "Имя: " + session.getAttribute("inputFIO") + "\n" +
                        "Группа: " + session.getAttribute("inputGroup") + "\n" +
                        "Курс: " + session.getAttribute("inputCourse"));
        saveResultsToServer(answersHolder);
        model.addAttribute("result", answersHolder.produceStringAnswer(questions));
        return "result";
    }
}
