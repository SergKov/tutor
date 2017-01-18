package com.getprepared.utils;

import com.getprepared.domain.*;
import com.getprepared.exception.ValidationException;

/**
 * Created by koval on 09.01.2017.
 */
public interface Validation {

    void validateAnswer(Answer answer) throws ValidationException;

    void validateQuestion(Question question) throws ValidationException;

    void validateQuiz(Quiz quiz) throws ValidationException;

    void validateSpeciality(Speciality speciality) throws ValidationException;

    void validateResult(Result result) throws ValidationException;

    void validateUser(User user) throws ValidationException;

    void validateEmail(String email) throws ValidationException;

    void validateRole(String role) throws ValidationException;

    void validateName(String name) throws ValidationException;

    void validateSurname(String surname) throws ValidationException;

    void validatePassword(String password) throws ValidationException;

    void validateId(Long id) throws ValidationException;

    void validateEntity(Entity entity) throws ValidationException;
}
