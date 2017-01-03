package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 02.01.2017.
 */
public class User extends AbstractDTO {

    private final Role role;
    private String email;
    private String password;
    private String name;
    private String surname;
    private List<ResultDTO> results;

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

    public List<ResultDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultDTO> results) {
        this.results = results;
    }
}
