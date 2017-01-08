package com.getprepared.dao.impl;

import com.getprepared.domain.Entity;
import com.getprepared.infrastructure.connection.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by koval on 06.01.2017.
 */
public abstract class AbstractDao<T extends Entity> {

    protected abstract T getEntity(final ResultSet rs);

    protected Connection getConnection(final ConnectionProvider provider) {
        return provider.getConnection();
    }
}
