package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Type;
import com.getprepared.web.form.QuestionAddForm;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.getprepared.constant.ServerConstant.NAME;
import static java.util.Arrays.*;
import static org.junit.Assert.*;

/**
 * Created by koval on 08.04.2017.
 */
public class QuestionAddConverterTest {

    private final Converter<QuestionAddForm, Question> converter = new QuestionAddConverter();

    @Test
    public void requireConvert() throws Exception {
        final QuestionAddForm form = new QuestionAddForm();
        form.setText(NAME);
        form.setAnswersText(new String[]{"1", "2"});
        form.setAnswersType(new String[]{Type.CORRECT.name(), Type.INCORRECT.name()});
        final Question question = converter.convert(form);
        assertEquals(question.getText(), form.getText());
        final List<Answer> answers = question.getAnswers();
        final String[] formTextArray = form.getAnswersText();
        final String[] formTypeArray = form.getAnswersType();
        for (int i = 0; i < formTextArray.length; i++) {
            assertEquals(formTextArray[i], answers.get(i).getText());
            assertEquals(formTypeArray[i], answers.get(i).getType().name());
        }
    }

}