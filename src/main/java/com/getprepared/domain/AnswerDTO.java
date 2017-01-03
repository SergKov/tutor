package com.getprepared.domain;

/**
 * Created by koval on 31.12.2016.
 */
public class AnswerDTO extends AbstractDTO {

    public static final String QUESTION_ID_KEY = "question_id";
    public static final String TEXT_KEY = "text";
    public static final String TYPE_KEY = "type";

    private final Question question;
    private final String text;
    private final AnswerType type;

    public AnswerDTO(Long id, Question question, String text, AnswerType type) {
        super(id);
        this.question = question;
        this.text = text;
        this.type = type;
    }

    public Question getQuestion() {
        return question;
    }

    public String getText() {
        return text;
    }

    public AnswerType getType() {
        return type;
    }
}
