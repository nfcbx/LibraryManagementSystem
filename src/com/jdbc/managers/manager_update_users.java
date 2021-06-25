package com.jdbc.managers;

import com.jdbc.users.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Vector;

public class manager_update_users {
    public static boolean manager_change_users(int index, int user_id, String user_name, String user_password) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "update users set user_id = ?, user_name = ?, user_password = ? where user_index = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, user_id);
            stmt.setObject(2, user_name);
            stmt.setObject(3, user_password);
            stmt.setObject(4, index);
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //todo users删除  返回值错误（可以删除）
    public static boolean manager_delete_users(int user_id) {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql:///library_management_system?useSSL=false&characterEncoding=utf8", "root", "/*-w123l/*-");
            String sql = "delete from users where user_id = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setObject(1, user_id);
            Vector<User> users = manager_get.manager_get_users();
//            boolean flag = false;
//            for (int i = 0; i < users.size() - 1; i++) {
//                if (users.get(i).user_id == user_id) {
//                    System.out.println(users.get(i).user_id);
//                    flag = true;
//                    break;
//                }
//            }
//            if (flag) {
                stmt.executeUpdate();
                return true;
//            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
