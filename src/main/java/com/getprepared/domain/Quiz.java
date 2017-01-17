package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Quiz extends Entity {

    public static final String OWNER_ID_KEY = "owner_id";
    public static final String SPECIALITY_ID_KEY = "speciality_id";
    public static final String NAME_KEY = "name";

    private User user;
    private Speciality speciality;
    private String name;
    private List<Quiz> quizzes;

    public Quiz() { }

    public Quiz(Long id, User user, Speciality speciality, String name) {
        super(id);
        this.user = user;
        this.name = name;
        this.speciality = speciality;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
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
