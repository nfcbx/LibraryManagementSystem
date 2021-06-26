package com.jdbc.users;

import java.sql.*;

public class user_login {
    public static int user_login(int user_id, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "select user_password from users where user_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, user_id);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                if (password.equals(resultSet.getString(1)))
                    return user_id;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
