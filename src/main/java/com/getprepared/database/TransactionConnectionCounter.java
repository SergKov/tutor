package com.getprepared.database;

import java.sql.Connection;

/**
 * Created by koval on 28.01.2017.
 */
public class TransactionConnectionCounter {

    private Connection connection;
    private int count;

    public void increment() {
        count++;
    }

    public void decrement() {
        count--;
    }

    public boolean isZero() {
        return count == 0;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}