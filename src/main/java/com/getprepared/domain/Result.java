package com.getprepared.domain;

import java.time.LocalDateTime;

/**
 * Created by koval on 02.01.2017.
 */
public class Result extends Entity {

    public static final String QUIZ_ID_KEY = "quiz_id";
    public static final String USER_ID_KEY = "user_id";
    public static final String MARK_KEY = "mark";
    public static final String CREATION_DATETIME_KEY = "creation_datetime";

    private Quiz quiz;
    private User user;
    private Byte mark;
    private LocalDateTime creationDateTime;

    public Result() { }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Byte getMark() {
        return mark;
    }

    public void setMark(Byte mark) {
        this.mark = mark;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Result result = (Result) o;

        if (quiz != null ? !quiz.equals(result.quiz) : result.quiz != null) return false;
        if (user != null ? !user.equals(result.user) : result.user != null) return false;
        if (mark != null ? !mark.equals(result.mark) : result.mark != null) return false;
        return creationDateTime != null ? creationDateTime.equals(result.creationDateTime) : result.creationDateTime == null;
    }

    @Override
    public int hashCode() {
        int result = quiz != null ? quiz.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (mark != null ? mark.hashCode() : 0);
        result = 31 * result + (creationDateTime != null ? creationDateTime.hashCode() : 0);
        return result;
    }
}
