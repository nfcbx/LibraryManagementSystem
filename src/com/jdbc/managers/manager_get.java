package com.jdbc.managers;

import com.jdbc.books.Book;
import com.jdbc.users.User;

import java.sql.*;
import java.util.Vector;

import static com.tools.IsOrNot.isInteger;

public class manager_get {
    public static Vector<User> manager_get_users() {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        String sql = "select * from users order by user_index";
        Vector<User> userArrayList = new Vector<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                int inedx = result.getInt(1);
                int id = result.getInt(2);
                String name = result.getString(3);
                String password = result.getString(4);
                User user = new User(inedx, id, name, password);
                userArrayList.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return userArrayList;
    }

    public static Vector<Book> manager_get_books() {
        Connection connection = null;
        Statement statement = null;
        ResultSet result = null;
        String sql = "select * from books";
        Vector<Book> bookVector = new Vector<>();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            statement = connection.createStatement();
            result = statement.executeQuery(sql);
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String author = result.getString(3);
                String publishing_house = result.getString(4);
                Book book = new Book(id, name, author, publishing_house);
                bookVector.add(book);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            if (result != null) {
                try {
                    result.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return bookVector;
    }

    public static Vector<User> manager_get_users_by(String info) {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Vector<User> userArrayList = new Vector<>();

        if (isInteger(info)) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
                String sql = "select * from users where users.user_id like ? OR users.user_name like ?";
                stmt = conn.prepareStatement(sql);
                stmt.setObject(1, "%" + info + "%");
                stmt.setObject(2, "%" + info + "%");
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int inedx = resultSet.getInt(1);
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    String password = resultSet.getString(4);
                    User user = new User(inedx, id, name, password);
                    userArrayList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
                String sql = "select * from users where users.user_name like ?";
                stmt = conn.prepareStatement(sql);
                stmt.setObject(1, "%" + info + "%");
                resultSet = stmt.executeQuery();
                if (resultSet.next()) {
                    int inedx = resultSet.getInt(1);
                    int id = resultSet.getInt(2);
                    String name = resultSet.getString(3);
                    String password = resultSet.getString(4);
                    User user = new User(inedx, id, name, password);
                    userArrayList.add(user);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userArrayList;
    }

    public static Vector<Book> manager_get_books_by(String info) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        Vector<Book> bookVector = new Vector<>();

        if (isInteger(info)) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
                String sql = "select * from books where book_id like ? OR book_name like ?";
                stmt = conn.prepareStatement(sql);
                stmt.setObject(1, "%" + info + "%");
                stmt.setObject(2, "%" + info + "%");
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int book_id = resultSet.getInt(1);
                    String book_name = resultSet.getString(2);
                    String book_author = resultSet.getString(3);
                    String publishing_house = resultSet.getString(4);
                    Book book = new Book(book_id, book_name, book_author, publishing_house);
                    bookVector.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            try {
                conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
                String sql = "select * from books where book_name like ?";
                stmt = conn.prepareStatement(sql);
                stmt.setObject(1, "%" + info + "%");
                resultSet = stmt.executeQuery();
                while (resultSet.next()) {
                    int book_id = resultSet.getInt(1);
                    String book_name = resultSet.getString(2);
                    String book_author = resultSet.getString(3);
                    String publishing_house = resultSet.getString(4);
                    Book book = new Book(book_id, book_name, book_author, publishing_house);
                    bookVector.add(book);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookVector;
    }
}

