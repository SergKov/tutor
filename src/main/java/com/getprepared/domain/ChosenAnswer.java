package com.getprepared.domain;

/**
 * Created by koval on 02.01.2017.
 */
public class ChosenAnswer extends Entity {

    public static final String QUESTION_ID_KEY = "question_id";
    public static final String TEXT_KEY = "text";

    private QuestionHistory question;
    private String text;

    public ChosenAnswer() { }

    public ChosenAnswer(Long id, QuestionHistory question, String text) {
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
