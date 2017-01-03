package com.getprepared.domain;

/**
 * Created by koval on 02.01.2017.
 */
public class AnswerHistoryDTO extends AbstractDTO {

    private final QuestionHistoryDTO question;
    private final String text;

    public AnswerHistoryDTO(Long id, QuestionHistoryDTO question, String text) {
        super(id);
        this.question = question;
        this.text = text;
    }

    public QuestionHistoryDTO getQuestion() {
        return question;
    }

    public String getText() {
        return text;
    }
}
