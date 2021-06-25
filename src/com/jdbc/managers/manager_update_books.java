package com.jdbc.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class manager_update_books {

    public static boolean manager_add_books(int book_id, String book_name, String book_author, String publishing_house) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "insert into books(book_id, book_name, book_author, publishing_house) values (?, ?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, book_id);
            stmt.setObject(2, book_name);
            stmt.setObject(3, book_author);
            stmt.setObject(4, publishing_house);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    //todo 可以修改 但是返回值错误
    public static boolean manager_change_books(int new_book_id, int old_book_id, String book_name, String book_author, String publishing_house) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "update books set book_id = ?, book_name = ?, book_author = ?, publishing_house = ? where book_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, new_book_id);
            stmt.setObject(2, book_name);
            stmt.setObject(3, book_author);
            stmt.setObject(4, publishing_house);
            stmt.setObject(5, old_book_id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //todo 书籍删除  返回值错误（可以删除）
    public static boolean manager_delete_books(int book_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "delete from books where book_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, book_id);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
