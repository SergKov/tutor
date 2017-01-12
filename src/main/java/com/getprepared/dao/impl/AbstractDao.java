package com.getprepared.dao.impl;

import com.getprepared.domain.Entity;
import com.getprepared.infrastructure.connection.TransactionalConnectionProvider;
import com.getprepared.infrastructure.template.JdbcTemplate;
import com.getprepared.utils.PropertyUtils;

import java.sql.Connection;

/**
 * Created by koval on 06.01.2017.
 */
public abstract class AbstractDao<T extends Entity> {

    protected JdbcTemplate template;

    public AbstractDao(JdbcTemplate template) {
        this.template = template;
    }

    protected Connection getConnection(final TransactionalConnectionProvider provider) {
        return provider.getConnection();
    }

    protected PropertyUtils getPropertyUtils() {
        return PropertyUtils.getInstance();
    }
}
