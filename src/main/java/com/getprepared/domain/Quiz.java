package com.getprepared.domain;

import java.time.LocalTime;

/**
 * Created by koval on 31.12.2016.
 */
public class Quiz extends AbstractDTO {

    public static final String NAME_KEY = "name";

    private final String name;
    private LocalTime time;

    public Quiz(Long id, String name, LocalTime time) {
        super(id);
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
