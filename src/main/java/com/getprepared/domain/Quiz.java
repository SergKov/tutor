package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Quiz extends Entity {

    public static final String NAME_KEY = "name";

    private String name;
    private List<Quiz> quizzes;

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

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
