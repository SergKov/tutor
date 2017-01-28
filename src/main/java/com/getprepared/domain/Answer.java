package com.getprepared.domain;

/**
 * Created by koval on 31.12.2016.
 */
public class Answer extends Entity {

    public static final String QUESTION_ID_KEY = "question_id";
    public static final String TEXT_KEY = "text";
    public static final String TYPE_KEY = "type";

    private Question question;
    private String text;
    private AnswerType type;

    public Answer() { }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public AnswerType getType() {
        return type;
    }

    public void setType(AnswerType type) {
        this.type = type;
    }
}
