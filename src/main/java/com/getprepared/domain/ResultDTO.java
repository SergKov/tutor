package com.getprepared.domain;

import java.time.LocalDateTime;

/**
 * Created by koval on 02.01.2017.
 */
public class ResultDTO extends AbstractDTO {

    public static final String SPECIALITY_ID_KEY = "speciality_id";
    public static final String USER_ID_KEY = "user_id";
    public static final String MARK_KEY = "mark";
    public static final String CREATION_DATETIME_KEY = "creation_datetime";

    private final Quiz quiz;
    private final User user;
    private final Byte mark;
    private final LocalDateTime creationDateTime;

    public ResultDTO(Long id, Quiz quiz, User user, Byte mark, LocalDateTime creationDateTime) {
        super(id);
        this.quiz = quiz;
        this.user = user;
        this.mark = mark;
        this.creationDateTime = creationDateTime;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public User getUser() {
        return user;
    }

    public Byte getMark() {
        return mark;
    }

    public LocalDateTime getCreationDateTime() {
        return creationDateTime;
    }
}
