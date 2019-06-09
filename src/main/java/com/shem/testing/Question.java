package com.shem.testing;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Question {
    String theme;
    String question;
    List<String> options = new ArrayList<>();
    List<Integer> answers = new ArrayList<>();
    String token = UUID.randomUUID().toString();

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public List<Integer> getAnswers() {
        return answers;
    }

    public String getToken() {
        return token;
    }
}
