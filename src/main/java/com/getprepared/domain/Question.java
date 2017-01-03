package com.getprepared.domain;

import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Question extends AbstractDTO {

    public static final String QUIZ_ID_KEY = "quiz_id";
    public static final String TEXT_KEY = "text";

    private String text;
    private final Quiz quiz;
    private List<AnswerDTO> answers;

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

    public List<AnswerDTO> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswerDTO> answers) {
        this.answers = answers;
    }
}
