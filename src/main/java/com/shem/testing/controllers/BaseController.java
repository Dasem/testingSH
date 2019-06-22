package com.shem.testing.controllers;

import com.shem.testing.AnswersHolder;
import com.shem.testing.gui.FXController;
import com.shem.testing.services.QuestionFilterService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.FileWriter;
import java.io.IOException;


public abstract class BaseController {
    @Autowired
    protected QuestionFilterService questionFilterService;

    protected synchronized void saveResultsToServer(AnswersHolder body){
        try(FileWriter writer = new FileWriter("results.txt", true))
        {
            String resultForSave = body.produceSaveToServerString(questionFilterService.filter(body.getTokens()))
                    .replace("\n","\r\n");

            //В консоль
            System.out.println(resultForSave);
            //В гуи
            FXController.getInstance().addResult(resultForSave);

            writer.write(resultForSave);
            writer.append('\n');
            writer.flush();
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
}
