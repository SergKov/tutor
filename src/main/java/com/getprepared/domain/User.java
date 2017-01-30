package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 02.01.2017.
 */
public class User extends Entity {

    public static final String ROLE_KEY = "role";
    public static final String EMAIL_KEY = "email";
    public static final String PASSWORD_KEY = "password";
    public static final String NAME_KEY = "name";
    public static final String SURNAME_KEY = "surname";

    private Role role;
    private String name;
    private String surname;
    private String email;
    private String password;

    private List<Result> results;

    public User() { }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }
}
