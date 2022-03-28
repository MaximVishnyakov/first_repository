package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    //   private final UserServiceImpl tableSQL = new UserServiceImpl();
    //  private final Session session = Util.getSessionFactory().openSession();

    private final Connection conn = Util.getConnect();

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS tableusers(id INT not NULL  AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50), age INT, PRIMARY KEY ( id))");
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {

            statement.executeUpdate("DROP TABLE IF EXISTS tableusers");
            conn.commit();

        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        session.save(new User(name, lastName, age));
        transaction.commit();
        session.close();
        System.out.println("User с именем – " + name + " добавлен в базу данных");
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(session.get(User.class, id));
        transaction.commit();
        session.close();

    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getSessionFactory().openSession();
        return (List<User>) session.createQuery("FROM User").list();
    }

    @Override
    public void cleanUsersTable() {

        Session session = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createSQLQuery("TRUNCATE TABLE tableusers").addEntity(User.class);
        query.executeUpdate();

        transaction.commit();
        session.close();
    }
}
