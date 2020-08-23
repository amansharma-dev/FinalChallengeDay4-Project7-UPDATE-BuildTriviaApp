package com.example.finalchallengeday4_project7_update_buildtriviaapp.model;

public class Question {

    private String question;
    private boolean isAnswerTrue;

    public Question() {
    }

    public Question(String question, boolean isAnswerTrue) {
        this.question = question;
        this.isAnswerTrue = isAnswerTrue;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public boolean isAnswerTrue() {
        return isAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        isAnswerTrue = answerTrue;
    }
}
