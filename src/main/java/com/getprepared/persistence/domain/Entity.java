package com.getprepared.persistence.domain;

/**
 * Created by koval on 31.12.2016.
 */
public abstract class Entity {

    public static final String ID_KEY = "id";

    private Long id;

    public Entity() { }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEntityName() {
        return this.getClass().getSimpleName();
    }
}
