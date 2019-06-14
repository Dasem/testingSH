package com.shem.testing.services;

import com.shem.testing.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

@Service
public class FilterService {

    @Autowired
    XlsxParser xlsxParser;

    public List<Question> filterQuestionByThemes(Collection<String> themes) {
        List<Question> result = new ArrayList<>();
        for (Question question : xlsxParser.getQuestions()) {
            if (themes.contains(question.getTheme())) {
                result.add(question);
            }
        }
        return result;
    }

    public List<Question> filterQuestionByCount(List<Question> questions, int count) { //TODO: какое-то тупое обрезание
        if (count > questions.size()) {
            return questions;
        }
        return questions.subList(0,count);
    }
}
