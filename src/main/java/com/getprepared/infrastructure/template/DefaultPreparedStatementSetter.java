package com.getprepared.infrastructure.template;

import com.getprepared.infrastructure.template.function.PreparedStatementSetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by koval on 21.01.2017.
 */
public class DefaultPreparedStatementSetter implements PreparedStatementSetter {

    @Override
    public void setValues(PreparedStatement ps) throws SQLException { }
}
