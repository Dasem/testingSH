package com.shem.testing;

public class Answer {
    String token;
    Integer answer;

    public Answer(String token, Integer answer) {
        this.token = token;
        this.answer = answer;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getAnswer() {
        return answer;
    }

    public void setAnswer(Integer answer) {
        this.answer = answer;
    }
}
