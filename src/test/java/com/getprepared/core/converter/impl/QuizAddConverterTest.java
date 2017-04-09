package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.form.QuizAddForm;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static com.getprepared.constant.ServerConstant.NAME;

/**
 * Created by koval on 06.04.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class QuizAddConverterTest {

    private final Converter<QuizAddForm, Quiz> converter = new QuizAddConverter();

    @Test
    public void requireConvert() throws Exception {
        final QuizAddForm quizAddForm = new QuizAddForm();
        quizAddForm.setName(NAME);
        final Quiz quiz = converter.convert(quizAddForm);
        Assert.assertEquals(NAME, quiz.getName());
    }
}