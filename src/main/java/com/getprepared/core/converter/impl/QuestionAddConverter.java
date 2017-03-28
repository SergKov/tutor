package com.getprepared.core.converter.impl;

import com.getprepared.annotation.Component;
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
@Component
public class QuestionAddConverter implements Converter<QuestionAddForm, Question>{

    @Override
    public Question convert(QuestionAddForm form) {
        final Question question = new Question();
        question.setText(form.getText());
        final List<Answer> answers = new ArrayList<>();

        final String[] formAnswersText = form.getAnswersText();
        final String[] formAnswersType = form.getAnswersType();
        for (int i = 0; i < formAnswersText.length; i++) {
            final Answer answer = new Answer();
            answer.setText(formAnswersText[i]);
            answer.setType(Type.valueOf(formAnswersType[i]));
            answer.setQuestion(question);
            answers.add(answer);
        }

        question.setAnswers(answers);
        return question;
    }
}
