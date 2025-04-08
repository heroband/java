package org.example;

import org.example.interfaces.ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqliteConnectionManager implements ConnectionManager {
    private final String url;

    public SqliteConnectionManager(String dbName) {
        this.url = "jdbc:sqlite:" + dbName;
    }

    @Override
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }
}
