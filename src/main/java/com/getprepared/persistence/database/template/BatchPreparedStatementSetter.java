package com.getprepared.persistence.database.template;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by koval on 23.02.2017.
 */
public interface BatchPreparedStatementSetter {

    void setValues(PreparedStatement ps, int i) throws SQLException;

    int getBatchSize();
}
