package com.getprepared.converter.form;

import com.getprepared.annotation.NotEmpty;

/**
 * Created by koval on 08.03.2017.
 */
public class QuestionAddForm {

    @NotEmpty
    private String question;

    private String[] answers;

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
}
