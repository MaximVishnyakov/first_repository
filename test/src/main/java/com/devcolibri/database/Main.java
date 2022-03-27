package com.devcolibri.database;


import java.sql.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mydbtest";
        String user = "root";
        String password = "qwerty";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement statement = conn.createStatement()) {
            if (!conn.isClosed()) {
                System.out.println("Setting is true!");
            }

            statement.execute("INSERT INTO animal(anim_name, anim_desc) VALUES ('name', 'desc')");

        } catch (SQLException e) {
            System.out.println("not connect!");
        }

    }
}
