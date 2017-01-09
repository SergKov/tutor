package com.getprepared.dao.impl;

import com.getprepared.domain.Entity;
import com.getprepared.infrastructure.connection.ConnectionProvider;
import com.getprepared.infrastructure.connection.impl.TransactionalConnectionProvider;
import com.getprepared.utils.PropertyUtils;

import java.sql.Connection;
import java.sql.ResultSet;

/**
 * Created by koval on 06.01.2017.
 */
public abstract class AbstractDao<T extends Entity> {

    protected ConnectionProvider provider;

    private void init() {
        provider = TransactionalConnectionProvider.getInstance();
    }

    public AbstractDao() {
        init();
    }

    protected abstract T getEntity(final ResultSet rs);

    protected Connection getConnection(final ConnectionProvider provider) {
        return provider.getConnection();
    }

    protected PropertyUtils getPropertyUtils() {
        return PropertyUtils.getInstance();
    }


}
