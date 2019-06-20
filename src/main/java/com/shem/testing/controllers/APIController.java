package com.shem.testing.controllers;

import com.shem.testing.AnswersHolder;
import com.shem.testing.services.QuestionFilterService;
import com.shem.testing.services.XlsxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class APIController extends BaseController {

    @Autowired
    XlsxParser xlsxParser;

    @ResponseBody
    @PostMapping("/check")
    public String check(@RequestBody AnswersHolder body){
        saveResultsToServer(body);
        //Поздравляем, вы ответили на n (%) вопросов
        return body.produceStringAnswer(questionFilterService.filter(body.getTokens()));
    }

}
