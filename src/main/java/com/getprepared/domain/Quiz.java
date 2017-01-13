package com.getprepared.domain;

import java.time.LocalTime;
import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Quiz extends Entity {

    public static final String NAME_KEY = "name";
    public static final String SPECIALITY_ID_KEY = "speciality_id";

    private String name;
    private Speciality speciality;
    private List<Quiz> quizzes;

    public Quiz() { }

    public Quiz(Long id, String name, Speciality speciality) {
        super(id);
        this.name = name;
        this.speciality = speciality;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
