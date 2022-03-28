package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final Connection conn = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {

            try {
                statement.executeUpdate("CREATE TABLE IF NOT EXISTS tableusers(id INT not NULL  AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age INT, PRIMARY KEY ( id))");
                conn.commit();
            } catch (BatchUpdateException x) {
                x.printStackTrace();
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {


            try {
                statement.executeUpdate("DROP TABLE IF EXISTS tableusers");
                conn.commit();
            } catch (BatchUpdateException x) {
                x.printStackTrace();
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO  tableusers(name, lastName, age) VALUES (?, ?, ?)")) {

            try {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, lastName);
                preparedStatement.setByte(3, age);
                preparedStatement.executeUpdate();
                conn.commit();
            } catch (BatchUpdateException x) {
                x.printStackTrace();
                conn.rollback();
            }

            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM tableusers WHERE id = ? ")) {


            try {
                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();
                conn.commit();
            } catch (BatchUpdateException x) {
                x.printStackTrace();
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = conn.createStatement()) {


            try {
                ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM tableusers");

                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getLong("id"));
                    user.setName(resultSet.getString("name"));
                    user.setLastName(resultSet.getString("lastName"));
                    user.setAge(resultSet.getByte("age"));
                    users.add(user);
                }
                conn.commit();
            } catch (BatchUpdateException x) {
                x.printStackTrace();
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {


            try {
                statement.executeUpdate("TRUNCATE TABLE tableusers");
                conn.commit();
            } catch (BatchUpdateException x) {
                x.printStackTrace();
                conn.rollback();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
