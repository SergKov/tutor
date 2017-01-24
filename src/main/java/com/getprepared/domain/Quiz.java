package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Quiz extends Entity {

    public static final String NAME_KEY = "name";

    private String name;
    private List<Question> questions;

    public Quiz() { }

    public Quiz(Long id, String name) {
        super(id);
        this.name = name;
    }

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
}
