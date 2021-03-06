package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Quiz;
import com.getprepared.web.form.QuizUpdateForm;
import org.junit.Assert;
import org.junit.Test;

import static com.getprepared.constant.ServerConstant.ID;
import static com.getprepared.constant.ServerConstant.NAME;
import static com.getprepared.constant.ServerConstant.STRING_ID;
import static org.junit.Assert.*;

/**
 * Created by koval on 06.04.2017.
 */
public class QuizUpdateConverterTest {

    private final Converter<QuizUpdateForm, Quiz> converter = new QuizUpdateConverter();

    @Test
    public void requireConvert() throws Exception {
        final QuizUpdateForm quizUpdateForm = new QuizUpdateForm();
        quizUpdateForm.setId(STRING_ID);
        quizUpdateForm.setName(NAME);
        final Quiz quiz = converter.convert(quizUpdateForm);
        Assert.assertEquals(ID, quiz.getId());
        Assert.assertEquals(NAME, quiz.getName());
    }
}