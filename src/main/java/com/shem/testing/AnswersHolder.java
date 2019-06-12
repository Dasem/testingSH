package com.shem.testing;

import java.util.ArrayList;
import java.util.List;

public class AnswersHolder {

    List<Answer> answers;
    List<String> tokens;
    String userInfo;

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

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
        List<Question> rightAnswers = calculateRightAnswers(filteredQuestions);
        double percent = (1.0 * rightAnswers.size() / allQuestions) * 100;
        return "Вы ответили правильно на " + Math.round(percent) + "% вопросов (" + rightAnswers.size() + " из " + allQuestions + ")";
    }

    public String produceSaveToServerString(List<Question> filteredQuestions) {
        int allQuestions = filteredQuestions.size();
        List<Question> rightAnswers = calculateRightAnswers(filteredQuestions);
        double percent = (1.0 * rightAnswers.size() / allQuestions) * 100;
        filteredQuestions.removeAll(rightAnswers);//Сейчас тут неверные ответы
        return "Студент:\n" +
                userInfo + "\n" +
                "Правильно ответил на " + Math.round(percent) + "% вопросов (" + rightAnswers.size() + " из " + allQuestions + ")\n" +
                "Не верные ответы:\n" +
                getErrors(filteredQuestions) +
                "-------------------------------\n" +
                "";
    }

    private String getErrors(List<Question> wrongAnswers) {
        StringBuilder errors = new StringBuilder();
        for (Question question : wrongAnswers) {
            errors.append(" - ").append(question.getQuestion()).append("\n");
        }
        return errors.toString();
    }

    private List<Question> calculateRightAnswers(List<Question> filteredQuestions) {
        List<Question> rightAnswers = new ArrayList<>();
        for (Question question : filteredQuestions) {
            List<Integer> answersForQuestionToken = getAnswersForQuestionToken(question.getToken());
            if (question.getAnswers().containsAll(answersForQuestionToken)// если все ответы верные
                    && answersForQuestionToken.containsAll(question.getAnswers())) {
                rightAnswers.add(question);
            }
        }
        return rightAnswers;
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
