package com.getprepared.core.converter.impl;

import com.getprepared.annotation.Component;
import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.form.QuizAddForm;

/**
 * Created by koval on 23.03.2017.
 */
@Component
public class QuizAddConverter implements Converter<QuizAddForm, Quiz> {

    @Override
    public Quiz convert(final QuizAddForm form) {
        final Quiz quiz = new Quiz();
        quiz.setName(form.getName());
        return quiz;
    }
}
