package com.getprepared.utils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static com.getprepared.utils.SqlCallable.call;

/**
 * Created by koval on 08.01.2017.
 */
public class PreparedStatementUtils {

    private PreparedStatementUtils() { }

    public static int executeUpdate(final PreparedStatement preparedStatement) {
        return call(preparedStatement::executeUpdate, "Failed to executeUpdate");
    }

    public static ResultSet executeQuery(final PreparedStatement preparedStatement) {
        return call(preparedStatement::executeQuery, "Failed to executeQuery");
    }
}
