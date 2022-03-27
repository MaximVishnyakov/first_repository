package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/dbunit";
    private static final String user = "root";
    private static final String password = "qwerty";

    public Connection getConnect() {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            if (!conn.isClosed()) {
                return conn;
            }
        } catch (SQLException e) {
            System.out.println("not connect!");
        }
        return conn;
    }
}
