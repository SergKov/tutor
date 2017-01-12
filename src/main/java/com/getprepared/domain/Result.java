package com.getprepared.domain;

import java.time.LocalDateTime;

/**
 * Created by koval on 02.01.2017.
 */
public class Result extends Entity {

    public static final String USER_ID_KEY = "user_id";
    public static final String MARK_KEY = "mark";
    public static final String QUIZ_NAME_KEY = "quiz_name";
    public static final String CREATION_DATETIME_KEY = "creation_datetime";

    private User user;
    private Byte mark;
    private String quizName;
    private LocalDateTime creationDateTime;

    public Result() { }

    public Result(Long id, User user, Byte mark, String quizName, LocalDateTime creationDateTime) {
        super(id);
        this.user = user;
        this.mark = mark;
        this.quizName = quizName;
        this.creationDateTime = creationDateTime;
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

    public String getQuizName() {
        return quizName;
    }

    public void setQuizName(String quizName) {
        this.quizName = quizName;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }
}
