package com.getprepared.domain;

/**
 * Created by koval on 02.01.2017.
 */
public class QuestionHistory extends Entity {

    public static final String RESULT_ID_KEY = "result_id";
    public static final String TEXT_KEY = "text";
    public static final String TYPE_KEY = "type";

    private Result result;
    private String text;
    private AnswerType type;

    public QuestionHistory() { }

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
