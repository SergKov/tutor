package com.getprepared.utils;

import com.getprepared.domain.*;

/**
 * Created by koval on 09.01.2017.
 */
public interface Validation {

    void validateAnswer(Answer answer);

    void validateQuestion(Question question);

    void validateQuiz(Quiz quiz);

    void validateResult(Result result);

    void validateUser(User user);

    void validateEmail(String email);

    void validatePassword(String password);

    void validateId(Long id);

    void validateEntity(Entity entity);
}
