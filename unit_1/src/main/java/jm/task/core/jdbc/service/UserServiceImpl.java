package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final String tableName = "tableusers";

    public void createUsersTable() {

        try (Connection conn = Util.getConnect();
             Statement statement = conn.createStatement()) {

            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);

            if (rs.next()) {
                System.out.println("Table exists");
                return;
            }

            System.out.println("table is create!");
            String sql = "CREATE TABLE " + tableName +
                    "(id_db INT not NULL  AUTO_INCREMENT," +
                    " name_db VARCHAR(50), " +
                    " lastName_db VARCHAR(50), " +
                    " age_db INT, " +
                    " PRIMARY KEY ( id_db))";

            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {

        try (Connection conn = Util.getConnect();
             Statement statement = conn.createStatement()) {

            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, tableName, null);

            if (rs.next()) {
                String sql = "DROP TABLE " + tableName;
                statement.executeUpdate(sql);
                System.out.println("table delete!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {

        try (Connection conn = Util.getConnect()) {

            String sql = "INSERT INTO tableusers(name_db, lastName_db, age_db) VALUES (?, ?, ?)";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            System.out.println("User с именем – " + name + " добавлен в базу данных");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnect()) {

            String sql = "DELETE FROM " + tableName + " WHERE id_db = ? ";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();

        try (Connection conn = Util.getConnect();
             Statement statement = conn.createStatement()) {

            String sql = "SELECT id_db, name_db, lastName_db, age_db FROM " + tableName;
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id_db"));
                user.setName(resultSet.getString("name_db"));
                user.setLastName(resultSet.getString("lastName_db"));
                user.setAge(resultSet.getByte("age_db"));
                users.add(user);
            }
            return users;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnect();
             Statement statement = conn.createStatement()) {

            String sql = "TRUNCATE TABLE " + tableName;
            statement.executeUpdate(sql);
            System.out.println("table clean!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
