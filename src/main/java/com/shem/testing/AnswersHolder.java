package com.shem.testing;

import java.util.ArrayList;
import java.util.List;

public class AnswersHolder {

    List<Answer> answers;
    List<String> tokens;

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    public List<String> getTokens() {
        return tokens;
    }

    public void setTokens(List<String> tokens) {
        this.tokens = tokens;
    }

    public String produceStringAnswer(List<Question> filteredQuestions) {
        int allQuestions = filteredQuestions.size();
        double rightAnswers = 0; // чтобы процент норм считался
        for (Question question : filteredQuestions) {
            List<Integer> answersForQuestionToken = getAnswersForQuestionToken(question.getToken());
            if (question.getAnswers().containsAll(answersForQuestionToken)// если все ответы верные
                    && answersForQuestionToken.containsAll(question.getAnswers())) {
                rightAnswers++;
            }
        }
        return "Вы ответили правильно на " + (rightAnswers / allQuestions) * 100 + "% вопросов";
    }


    private List<Integer> getAnswersForQuestionToken(String token) {
        List<Integer> answersNumbers = new ArrayList<>();
        for (Answer answer : answers) {
            if (answer.getToken().equals(token)) {
                answersNumbers.add(answer.getAnswer());
            }
        }
        return answersNumbers;
    }
}
