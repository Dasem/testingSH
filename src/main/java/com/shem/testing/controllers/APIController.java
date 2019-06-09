package com.shem.testing.controllers;

import com.shem.testing.AnswersHolder;
import com.shem.testing.services.QuestionFilterService;
import com.shem.testing.services.XlsxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class APIController {

    @Autowired
    XlsxParser xlsxParser;

    @Autowired
    QuestionFilterService questionFilterService;

    @ResponseBody
    @PostMapping("/check")
    public String check(@RequestBody AnswersHolder body){

        //Поздравляем, вы ответили на n (%) вопросов
        return body.produceStringAnswer(questionFilterService.filter(body.getTokens()));
    }

//    private String checkAnswers(List<Answer> answers){
//        List<Question> questions = xlsxParser.getQuestions();
//
//
//    }
}
