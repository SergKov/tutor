package com.getprepared.controller.dto;

import com.getprepared.domain.Answer;
import com.getprepared.domain.Question;

import java.util.List;

/**
 * Created by koval on 04.02.2017.
 */
public class TestQuestion {

    private Question question;
    private List<Answer> answers;

    public TestQuestion() { }

    public TestQuestion(Question question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
