package com.getprepared.util.impl;

import com.getprepared.annotation.Bean;
import com.getprepared.domain.*;
import com.getprepared.exception.ValidationException;
import com.getprepared.util.Validation;
import org.apache.commons.lang3.StringUtils;

import static com.getprepared.constant.UtilsConstant.REGEX;

/**
 * Created by koval on 14.01.2017.
 */
@Bean("validation")
public class ValidationImpl implements Validation {

    @Override
    public void validateAnswer(final Answer answer) throws ValidationException {
        validateEntity(answer);

        if (answer.getQuestion() == null) {
            throw new ValidationException("Answer's question is missing.");
        }

        validateText(answer.getText());
        validateType(answer.getType());
    }

    private void validateType(final AnswerType type) throws ValidationException {
        if (type == null) {
            throw new ValidationException("Type is missing.");
        }
    }

    @Override
    public void validateQuestion(final Question question) throws ValidationException {
        validateEntity(question);

        if (question.getAnswers().size() < 2) {
            throw new ValidationException("Question does not have enough answers");
        }

        validateText(question.getText());
    }

    private void validateText(final String text) throws ValidationException {
        if (!StringUtils.isNotEmpty(text)) {
            throw new ValidationException(String.format("Text is missing: %s", text));
        }
    }

    @Override
    public void validateQuiz(final Quiz quiz) throws ValidationException {
        validateEntity(quiz);
        validateQuizName(quiz.getName());
    }

    private void validateQuizName(final String name) throws ValidationException {
        if (name == null) {
            throw new ValidationException("Quiz's name is missing.");
        }

        if (!name.matches(REGEX.QUIZ_NAME)) {
            throw new ValidationException(String.format("Quiz's name %s has an incorrect format.", name));
        }
    }

    @Override
    public void validateUser(final User user) throws ValidationException {
        if (user == null) {
            throw new ValidationException("User is missing.");
        }

        validateRole(user.getRole());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateName(user.getName());
        validateSurname(user.getSurname());
    }

    private void validateName(final String name) throws ValidationException {
        if (name == null) {
            throw new ValidationException("Name is missing.");
        }
        if (!name.matches(REGEX.NAME)) {
            throw new ValidationException(String.format("Name %s has an incorrect format.", name));
        }
    }

    private void validateSurname(final String surname) throws ValidationException {
        if (surname == null) {
            throw new ValidationException("Surname is missing.");
        }
        if (!surname.matches(REGEX.SURNAME)) {
            throw new ValidationException(String.format("Surname %s has an incorrect format.", surname));
        }
    }

    @Override
    public void validateEmail(final String email) throws ValidationException {
        if (email == null) {
            throw new ValidationException("Email is missing.");
        }
        if (!email.matches(REGEX.EMAIL)) {
            throw new ValidationException(String.format("Email %s has an incorrect format.", email));
        }
    }

    private void validateRole(final Role role) throws ValidationException {
        if (role == null) {
            throw new ValidationException("Role is missing.");
        }
    }

    @Override
    public void validatePassword(final String password) throws ValidationException {
        if (password == null) {
            throw new ValidationException("Password is missing.");
        }
        if (!password.matches(REGEX.PASSWORD)) {
            throw new ValidationException(String.format("Password %s has an incorrect format.", password));
        }
    }

    @Override
    public void validateId(final Long id) throws ValidationException {
        if (id == null) {
            throw new ValidationException("ID is missing.");
        }
        if (id <= 0) {
            throw new ValidationException(String.format("ID %d is less or equals 0.", id));
        }
    }

    private void validateEntity(Entity entity) throws ValidationException {
        if (entity == null) {
            throw new ValidationException(String.format("%s is missing.", entity.getEntityName()));
        }
    }
}
