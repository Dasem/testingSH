package com.shem.testing.services;

import com.shem.testing.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class FilterService {

    @Autowired
    XlsxParser xlsxParser;

    public List<Question> filterQuestion(Collection<String> themes) {
        List<Question> result = new ArrayList<>();
        for (Question question : xlsxParser.getQuestions()) {
            if (themes.contains(question.getTheme())) {
                result.add(question);
            }
        }
        return result;
    }
}
