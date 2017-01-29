package com.getprepared.dao.impl;

import com.getprepared.domain.Entity;
import com.getprepared.database.template.JdbcTemplate;

/**
 * Created by koval on 06.01.2017.
 */
public abstract class AbstractDao<T extends Entity> {

    private JdbcTemplate jdbcTemplate;

    public AbstractDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
