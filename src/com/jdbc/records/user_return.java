package com.jdbc.records;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class user_return {
    public static void user_return_book(int user_id, int book_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "delete from records where user_id = ? AND book_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, user_id);
            stmt.setObject(2,book_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
