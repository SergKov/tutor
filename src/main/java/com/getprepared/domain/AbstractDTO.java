package com.getprepared.domain;

/**
 * Created by koval on 31.12.2016.
 */
public abstract class AbstractDTO {

    public static final String ID_KEY = "id";

    private final Long id;

    public AbstractDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
