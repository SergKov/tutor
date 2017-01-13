package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 13.01.2017.
 */
public class Speciality extends Entity {

    private static final String NAME_KEY = "name";

    private String name;
    private List<Quiz> quizzes;

    public Speciality() { }

    public Speciality(Long id, String name) {
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
