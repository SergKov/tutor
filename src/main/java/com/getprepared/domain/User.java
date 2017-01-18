package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 02.01.2017.
 */
public class User extends Entity {

    public static final String ROLE_KEY = "role";
    public static final String EMAIL_KEY = "homePage.email";
    public static final String PASSWORD_KEY = "homePage.password";
    public static final String NAME_KEY = "signUp.name";
    public static final String SURNAME_KEY = "signUp.surname";

    private Role role;
    private String email;
    private String password;
    private String name;
    private String surname;
    private List<Result> results;
    private List<Quiz> quizzes;

    public User() { }

    public User(Long id, Role role, String email, String password, String name, String surname) {
        super(id);
        this.role = role;
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public List<Quiz> getQuizzes() {
        return quizzes;
    }

    public void setQuizzes(List<Quiz> quizzes) {
        this.quizzes = quizzes;
    }
}
