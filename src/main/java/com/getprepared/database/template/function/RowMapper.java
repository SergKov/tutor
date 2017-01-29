package com.getprepared.database.template.function;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koval on 11.01.2017.
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs) throws SQLException;
}
