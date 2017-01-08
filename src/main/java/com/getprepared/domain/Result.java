package com.getprepared.domain;

import java.time.LocalDateTime;

/**
 * Created by koval on 02.01.2017.
 */
public class Result extends Entity {

    public static final String SPECIALITY_ID_KEY = "speciality_id";
    public static final String USER_ID_KEY = "user_id";
    public static final String MARK_KEY = "mark";
    public static final String CREATION_DATETIME_KEY = "creation_datetime";

    private Quiz quiz;
    private User user;
    private Byte mark;
    private LocalDateTime creationDateTime;

    public Result(Long id, Quiz quiz, User user, Byte mark, LocalDateTime creationDateTime) {
        super(id);
        this.quiz = quiz;
        this.user = user;
        this.mark = mark;
        this.creationDateTime = creationDateTime;
    }

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
}
