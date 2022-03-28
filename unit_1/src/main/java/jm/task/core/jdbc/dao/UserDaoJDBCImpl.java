package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class UserDaoJDBCImpl implements UserDao {
    private final String tableName = "tableusers";
    private final Connection conn = Util.getConnect();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " + tableName + "(id INT not NULL  AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age INT, PRIMARY KEY ( id))");
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS " + tableName);
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + tableName + "(name, lastName, age) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            conn.commit();

            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {

        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM " + tableName + " WHERE id = ? ")) {


            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Statement statement = conn.createStatement()) {

            ResultSet resultSet = statement.executeQuery("SELECT id, name, lastName, age FROM " + tableName);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));
                users.add(user);
            }
            conn.commit();
            return users;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {

            statement.executeUpdate("TRUNCATE TABLE " + tableName);
            conn.commit();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
