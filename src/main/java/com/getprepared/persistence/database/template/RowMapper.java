package com.getprepared.persistence.database.template;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by koval on 11.01.2017.
 */
public interface RowMapper<T> {

    T mapRow(ResultSet rs) throws SQLException;
}
