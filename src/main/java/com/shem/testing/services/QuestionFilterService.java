package com.shem.testing.services;

import com.shem.testing.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionFilterService {

    @Autowired
    XlsxParser xlsxParser;

    public List<Question> filter(List<String> tokens){
        ArrayList<Question> questions = new ArrayList<>();
        for (Question question:xlsxParser.getQuestions()){
            if (tokens.contains(question.getToken())){
                questions.add(question);
            }
        }
        return questions;
    }
}
