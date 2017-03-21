package com.getprepared.persistence.domain;

import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Quiz extends Entity {

    public static final String NAME_KEY = "name";

    private String name;
    private List<Question> questions;

    public Quiz() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Quiz quiz = (Quiz) o;

        if (name != null ? !name.equals(quiz.name) : quiz.name != null) return false;
        return questions != null ? questions.equals(quiz.questions) : quiz.questions == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (questions != null ? questions.hashCode() : 0);
        return result;
    }
}
