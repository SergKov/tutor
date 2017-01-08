package com.getprepared.domain;

import java.time.LocalTime;

/**
 * Created by koval on 31.12.2016.
 */
public class Quiz extends Entity {

    public static final String NAME_KEY = "name";
    public static final String TIME_KEY = "time";

    private String name;
    private LocalTime time;

    public Quiz(Long id, String name, LocalTime time) {
        super(id);
        this.name = name;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }
}
