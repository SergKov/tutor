package com.getprepared.infrastructure.connection;

import java.sql.Connection;

/**
 * Created by koval on 02.01.2017.
 */
public interface ConnectionProvider {

    Connection getConnection();
}
