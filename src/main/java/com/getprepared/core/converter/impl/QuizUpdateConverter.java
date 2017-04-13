package com.getprepared.core.converter.impl;

import com.getprepared.annotation.Component;
import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.form.QuizUpdateForm;

/**
 * Created by koval on 01.04.2017.
 */
@Component
public class QuizUpdateConverter implements Converter<QuizUpdateForm, Quiz> {

    @Override
    public Quiz convert(final QuizUpdateForm form) {
        final Quiz quiz = new Quiz();
        quiz.setId(Long.valueOf(form.getId()));
        quiz.setName(form.getName());
        return quiz;
    }
}
