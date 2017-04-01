package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.form.QuizUpdateForm;

/**
 * Created by koval on 01.04.2017.
 */
public class QuizUpdateConverter implements Converter<QuizUpdateForm, Quiz> {

    @Override
    public Quiz convert(final QuizUpdateForm form) {
        final Quiz quiz = new Quiz();
        quiz.setName(form.getName());
        return quiz;
    }
}
