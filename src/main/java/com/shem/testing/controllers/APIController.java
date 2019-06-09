package com.shem.testing.controllers;

import com.shem.testing.Answer;
import com.shem.testing.AnswersHolder;
import com.shem.testing.Question;
import com.shem.testing.parsers.XlsxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class APIController {

    @Autowired
    XlsxParser xlsxParser;

    @ResponseBody
    @PostMapping("/check")
    public String check(@RequestParam Map<String, String> body){

        //Поздравляем, вы ответили на n (%) вопросов
        return "";
    }

//    private String checkAnswers(List<Answer> answers){
//        List<Question> questions = xlsxParser.getQuestions();
//
//
//    }
}
