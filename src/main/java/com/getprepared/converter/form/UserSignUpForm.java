package com.getprepared.converter.form;

import com.getprepared.annotation.UserRole;
import com.getprepared.annotation.Pattern;
import com.getprepared.domain.Role;

import static com.getprepared.constant.UtilsConstant.REGEX;

/**
 * Created by koval on 08.03.2017.
 */
public class UserSignUpForm {

    @UserRole({Role.STUDENT, Role.TUTOR})
    private String role;

    @Pattern(regexp = REGEX.NAME)
    private String name;

    @Pattern(regexp = REGEX.SURNAME)
    private String surname;

    @Pattern(regexp = REGEX.EMAIL)
    private String email;

    @Pattern(regexp = REGEX.PASSWORD)
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
