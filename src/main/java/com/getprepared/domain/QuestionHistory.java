package com.getprepared.domain;

/**
 * Created by koval on 02.01.2017.
 */
public class QuestionHistory extends Entity {

    private Result result;
    private String text;
    private AnswerType type;

    public QuestionHistory(Long id, Result result, String text, AnswerType type) {
        super(id);
        this.result = result;
        this.text = text;
        this.type = type;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
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
