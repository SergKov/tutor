package com.getprepared.web.form;

import com.getprepared.persistence.domain.Type;
import com.getprepared.web.validation.annotation.AnswerType;
import com.getprepared.web.validation.annotation.NotEmpty;
import com.getprepared.web.validation.annotation.Size;

/**
 * Created by koval on 08.03.2017.
 */
public class QuestionAddForm {

    @NotEmpty
    private String text;

    @Size(min = 2, max = 10)
    private String[] answersText;

    @Size(min = 2, max = 10)
    @AnswerType({Type.INCORRECT, Type.CORRECT})
    private String[] answersType;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getAnswersText() {
        return answersText;
    }

    public void setAnswersText(String[] answersText) {
        this.answersText = answersText;
    }

    public String[] getAnswersType() {
        return answersType;
    }

    public void setAnswersType(String[] answersType) {
        this.answersType = answersType;
    }
}
