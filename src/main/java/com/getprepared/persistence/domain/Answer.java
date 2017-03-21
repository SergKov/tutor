package com.getprepared.persistence.domain;

/**
 * Created by koval on 31.12.2016.
 */
public class Answer extends Entity {

    public static final String QUESTION_ID_KEY = "question_id";
    public static final String TEXT_KEY = "text";
    public static final String TYPE_KEY = "type";

    private Question question;
    private String text;
    private Type type;

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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer answer = (Answer) o;

        if (question != null ? !question.equals(answer.question) : answer.question != null) return false;
        if (text != null ? !text.equals(answer.text) : answer.text != null) return false;
        return type == answer.type;
    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }
}
