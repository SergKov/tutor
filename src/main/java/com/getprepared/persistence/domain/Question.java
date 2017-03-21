package com.getprepared.persistence.domain;

import java.util.List;

/**
 * Created by koval on 31.12.2016.
 */
public class Question extends Entity {

    public static final String QUIZ_ID_KEY = "quiz_id";
    public static final String TEXT_KEY = "text";

    private Quiz quiz;
    private String text;
    private List<Answer> answers;

    public Question() { }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question question = (Question) o;

        if (quiz != null ? !quiz.equals(question.quiz) : question.quiz != null) return false;
        if (text != null ? !text.equals(question.text) : question.text != null) return false;
        return answers != null ? answers.equals(question.answers) : question.answers == null;
    }

    @Override
    public int hashCode() {
        int result = quiz != null ? quiz.hashCode() : 0;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (answers != null ? answers.hashCode() : 0);
        return result;
    }
}
