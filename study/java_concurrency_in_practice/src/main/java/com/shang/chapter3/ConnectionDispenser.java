package com.shang.chapter3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * ConnectionDispenser
 * <p/>
 * Using ThreadLocal to ensure thread confinement
 * 使用ThreadLocal维持线程封闭性
 *
 * @author Brian Goetz and Tim Peierls
 */
public class ConnectionDispenser {
    static String DB_URL = "jdbc:mysql://localhost/mydatabase";

    private ThreadLocal<Connection> connectionHolder
            = new ThreadLocal<Connection>() {
                public Connection initialValue() {
                    try {
                        return DriverManager.getConnection(DB_URL);
                    } catch (SQLException e) {
                        throw new RuntimeException("Unable to acquire Connection, e");
                    }
                };
            };

    public Connection getConnection() {
        return connectionHolder.get();
    }
}
