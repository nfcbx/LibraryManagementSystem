package com.jdbc.users;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class user_register {
    public static boolean user_register(int user_id, String user_name, String user_password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "insert into users(user_id, user_name, user_password) values (?, ?, ?);";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, user_id);
            stmt.setObject(2, user_name);
            stmt.setObject(3, user_password);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
}
