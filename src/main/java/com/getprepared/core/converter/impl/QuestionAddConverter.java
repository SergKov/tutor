package com.getprepared.core.converter.impl;

import com.getprepared.core.converter.Converter;
import com.getprepared.persistence.domain.Answer;
import com.getprepared.persistence.domain.Question;
import com.getprepared.persistence.domain.Type;
import com.getprepared.web.form.QuestionAddForm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by koval on 23.03.2017.
 */
public class QuestionAddConverter implements Converter<QuestionAddForm, Question>{

    @Override
    public Question convert(QuestionAddForm form) {
        final Question question = new Question();
        question.setText(form.getText());
        final List<Answer> answers = new ArrayList<>();

        final String[] formAnswers = form.getAnswersText();
        for (int i = 0; i < formAnswers.length; i++) {
            final Answer answer = new Answer();
            answer.setText(formAnswers[i]);
            answer.setType(Type.valueOf(formAnswers[i]));
            answer.setQuestion(question);
            answers.add(answer);
        }

        question.setAnswers(answers);
        return question;
    }
}
