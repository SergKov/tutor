package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Question extends Entity {

    public static final String QUIZ_ID_KEY = "quiz_id";
    public static final String TEXT_KEY = "text";

    private String text;
    private Quiz quiz;
    private List<Answer> answers;

    public Question() {
    }

    public Question(Long id, String text, Quiz quiz) {
        super(id);
        this.text = text;
        this.quiz = quiz;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
