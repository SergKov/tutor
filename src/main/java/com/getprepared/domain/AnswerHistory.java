package com.getprepared.domain;

/**
 * Created by koval on 02.01.2017.
 */
public class AnswerHistory extends Entity {

    private QuestionHistory question;
    private String text;

    public AnswerHistory() {
    }

    public AnswerHistory(Long id, QuestionHistory question, String text) {
        super(id);
        this.question = question;
        this.text = text;
    }

    public QuestionHistory getQuestion() {
        return question;
    }

    public String getText() {
        return text;
    }
}
