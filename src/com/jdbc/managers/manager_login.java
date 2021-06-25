package com.jdbc.managers;

import java.sql.*;

public class manager_login {
    public static boolean manager_login(int manager_id, String password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "select manager_password from managers where manager_id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, manager_id);
            resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                if (password.equals(resultSet.getString(1)))
                    return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
