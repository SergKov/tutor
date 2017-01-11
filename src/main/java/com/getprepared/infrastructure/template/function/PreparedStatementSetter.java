package com.getprepared.infrastructure.template.function;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by koval on 11.01.2017.
 */
@FunctionalInterface
public interface PreparedStatementSetter {

    void setValues(PreparedStatement ps) throws SQLException;
}
