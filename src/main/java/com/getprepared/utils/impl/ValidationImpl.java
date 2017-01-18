package com.getprepared.utils.impl;

import com.getprepared.domain.*;
import com.getprepared.exception.ValidationException;
import com.getprepared.utils.Validation;
import org.apache.commons.lang3.StringUtils;

import static com.getprepared.constant.UtilsConstant.REGEX;

/**
 * Created by koval on 14.01.2017.
 */
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

        if (quiz.getSpeciality() != null) {
            throw new ValidationException("Quiz's speciality is missing.");
        }

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
    public void validateSpeciality(final Speciality speciality) throws ValidationException {
        validateEntity(speciality);
        validateSpecialityName(speciality.getName());
    }

    private void validateSpecialityName(final String name) throws ValidationException {

        if (name == null) {
            throw new ValidationException("Speciality's name is missing.");
        }

        if (!name.matches(REGEX.SPECIALITY_NAME)) {
            throw new ValidationException(String.format("Speciality's name %s has an incorrect format.", name));
        }
    }

    @Override
    public void validateResult(final Result result) throws ValidationException {

        if (result == null) {
            throw new ValidationException("Result is missing.");
        }

        if (result.getUser() == null) {
            throw new ValidationException("Result's user is missing.");
        }

        if (result.getCreationDateTime() == null) {
            throw new ValidationException("Result's creation date is missing.");
        }

        validateName(result.getSpecialityName());
        validateName(result.getQuizName());
        validateMark(result.getMark());

    }

    private void validateMark(final Byte mark) throws ValidationException {
        if (mark == null) {
            throw new ValidationException("Mark is missing.");
        }
        if (mark > 100 || mark < 0) {
            throw new ValidationException(String.format("Mark %d is beyond 0-100 bounds : ", mark));
        }
    }

    @Override
    public void validateUser(final User user) throws ValidationException {

        if (user == null) {
            throw new ValidationException("User is missing.");
        }

        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateName(user.getName());
        validateSurname(user.getSurname());
    }

    public void validateName(final String name) throws ValidationException {
        if (name == null) {
            throw new ValidationException("Name is missing.");
        }
        if (!name.matches(REGEX.NAME)) {
            throw new ValidationException(String.format("Name %s has an incorrect format.", name));
        }
    }

    public void validateSurname(final String surname) throws ValidationException {
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

    @Override
    public void validateRole(final String role) throws ValidationException {
        if (role == null) {
            throw new ValidationException("Role is missing.");
        }
        if (!role.equals("STUDENT") && !role.equals("TUTOR")) {
            throw new ValidationException("Role is illegal");
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

    @Override
    public void validateEntity(Entity entity) throws ValidationException {
        if (entity == null) {
            throw new ValidationException(String.format("%s is missing.", entity.getEntityName()));
        }
    }
}
