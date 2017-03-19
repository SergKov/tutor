package com.getprepared.converter.form;

import com.getprepared.annotation.Pattern;

import static com.getprepared.constant.UtilsConstant.REGEX;

/**
 * Created by koval on 08.03.2017.
 */
public class UserSignUpForm {

    @Pattern(regexp = REGEX.ROLE)
    private String role;

    @Pattern(regexp = "^[A-z]{1}[a-z]{1,19}|[А-я]{1}[а-я]{1,19}$")
    private String name;

    @Pattern(regexp = "^[A-z]{1}[a-z]{1,19}|[А-я]{1}[а-я]{1,19}$")
    private String surname;

    @Pattern(regexp = REGEX.EMAIL)
    private String email;

    @Pattern(regexp = "^[A-z]{1}[a-z]{1,19}|[А-я]{1}[а-я]{1,19}$")
    private String password;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
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
}
