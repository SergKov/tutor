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
    private String question;

    @Size(min = 2, max = 10)
    private String[] answers;

    @Size(min = 2, max = 10)
    @AnswerType({Type.INCORRECT, Type.CORRECT})
    private String[] answerType;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String[] getAnswerType() {
        return answerType;
    }

    public void setAnswerType(String[] answerType) {
        this.answerType = answerType;
    }
}
