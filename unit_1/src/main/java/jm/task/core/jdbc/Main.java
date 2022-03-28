package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

       // UserServiceImpl uDH = new UserServiceImpl();
        UserDaoHibernateImpl uDH = new UserDaoHibernateImpl();
        uDH.createUsersTable();
        uDH.saveUser("Alex", "Luck", (byte) 119);
        uDH.saveUser("Mike", "Nevada", (byte) 15);
        uDH.saveUser("Ann", "Loser", (byte) 30);
        uDH.saveUser("Felix", "Paul", (byte) 55);

        uDH.removeUserById(2);

        List<User> users = new ArrayList<>(uDH.getAllUsers());
        users.forEach(System.out::println);


        uDH.cleanUsersTable();

        uDH.dropUsersTable();

//        Создание таблицы User(ов)

//        UserServiceImpl tableSQL = new UserServiceImpl();
//        tableSQL.createUsersTable();
//
////        Добавление 4 User(ов) в таблицу с данными на свой выбор. После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
//
//        tableSQL.saveUser("Alex", "Luck", (byte) 119);
//        tableSQL.saveUser("Mike", "Nevada", (byte) 15);
//        tableSQL.saveUser("Ann", "Loser", (byte) 30);
//        tableSQL.saveUser("Felix", "Paul", (byte) 55);
//
////        Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User)
//
//        List<User> users = new ArrayList<>(tableSQL.getAllUsers());
//        users.forEach(System.out::println);
//
////        Очистка таблицы User(ов)
//
//        tableSQL.removeUserById(2);
//        tableSQL.cleanUsersTable();
//
////        Удаление таблицы
//      tableSQL.dropUsersTable();
//

    }
}
