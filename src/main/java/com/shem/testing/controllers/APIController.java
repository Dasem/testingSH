package com.shem.testing.controllers;

import com.shem.testing.AnswersHolder;
import com.shem.testing.services.QuestionFilterService;
import com.shem.testing.services.XlsxParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.FileWriter;
import java.io.IOException;

@RestController
public class APIController {

    @Autowired
    XlsxParser xlsxParser;

    @Autowired
    QuestionFilterService questionFilterService;

    @ResponseBody
    @PostMapping("/check")
    public String check(@RequestBody AnswersHolder body){
        saveResultsToServer(body);
        //Поздравляем, вы ответили на n (%) вопросов
        return body.produceStringAnswer(questionFilterService.filter(body.getTokens()));
    }

    private synchronized void saveResultsToServer(AnswersHolder body){
        try(FileWriter writer = new FileWriter("results.txt", true))
        {
            String resultForSave = body.produceSaveToServerString(questionFilterService.filter(body.getTokens()))
                    .replace("\n","\r\n");
            System.out.println(resultForSave);
            writer.write(resultForSave);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
